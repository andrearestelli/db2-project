DROP TRIGGER IF EXISTS `telcodb`.`UpdateInsolvent`;

DELIMITER $$
USE `telcodb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `UpdateInsolvent` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF ((old.state ='PENDING' OR old.state = 'REJECTED') AND new.state = 'REJECTED') THEN
			UPDATE telcodb.customer
			SET insolvent = insolvent + 1
			WHERE username = old.user_orderer;
	ELSEIF (old.state ='REJECTED' AND new.state = 'VALID') THEN
			UPDATE telcodb.customer
			SET insolvent = insolvent - 1
			WHERE username = old.user_orderer;
	END IF;
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `telcodb`.`UpdateTotalPurchasesPerPackage`;

DELIMITER $$
USE `telcodb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `UpdateTotalPurchasesPerPackage` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.total_purchases_per_package
			SET total_purchases = total_purchases + 1
			WHERE packageName = (SELECT sp.name FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
	END IF;
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `telcodb`.`UpdateTotalPurchasesPerPackageAndValidityPeriod`;

DELIMITER $$
USE `telcodb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `UpdateTotalPurchasesPerPackageAndValidityPeriod` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.total_purchases_validity_period_per_package
			SET total_purchases = total_purchases + 1
			WHERE (packageName,validity_period) = (SELECT sp.name,sp.validity_period FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
	END IF;
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `telcodb`.`UpdateTotalValueServicePackages`;

DELIMITER $$
USE `telcodb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `UpdateTotalValueServicePackages` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.total_value_of_sales
			SET total_value_with_optp = total_value_with_optp + new.total_value
			WHERE packageName = (SELECT sp.name FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
		UPDATE telcodb.total_value_of_sales
			SET total_value_without_optp = total_value_without_optp + (SELECT sp1.monthly_fee*sp1.validity_period FROM
																		telcodb.service_package sp1 JOIN telcodb.order o1
																		ON o1.id_package = sp1.ID
																		WHERE o1.ID = new.ID )
			WHERE packageName = (SELECT sp.name FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
	END IF;
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `telcodb`.`UpdateAverageOptProducts`;

DELIMITER $$
USE `telcodb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `UpdateAverageOptProducts` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
		IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.average_number_of_optional_products
			SET number_of_sales_package = number_of_sales_package + 1,
				number_of_opt_products = number_of_opt_products + (SELECT COUNT(*) 
																	FROM telcodb.order_opt_product_link optl
                                                                    WHERE optl.IDorder = new.ID),
				average_of_opt_products = number_of_opt_products/number_of_sales_package
			WHERE packageName = (SELECT sp.name FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
		END IF;
END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `telcodb`.`UpdateBestSellerOptProduct`;

DELIMITER $$
USE `telcodb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `UpdateBestSellerOptProduct` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.best_seller_optional_product
        SET total_value = total_value + (SELECT optProd.monthly_fee * optProd.validity_period 
										FROM telcodb.order_opt_product_link optLink JOIN telcodb.optional_product optProd
										ON optProd.ID = optLink.ID_optional_product
                                        WHERE optLink.IDorder = new.ID AND optProd.name = optionalProduct)
		WHERE optionalProduct IN (SELECT op.name FROM
								telcodb.optional_product op JOIN telcodb.order_opt_product_link o
                                ON o.id_optional_product = op.ID
                                WHERE o.IDorder = new.ID);
		END IF;
END
$$
DELIMITER ;