function sendOptionalProducts(trigger){
    var form = trigger.closest("form");
    if(form.checkValidity()) {
        var optionalProductList = Array.prototype.slice.call(form.querySelectorAll("input[name='optionalProductID']:checked"))
            .map(function (x){
                return x.value;
            });
        var servicePackageID = form.querySelector("input[name = 'servicePackageSelected']:checked").value;
        var subscriptionDate = form.querySelector("input[name = 'subscriptionDate']").value;
        form = {
            "servicePackageID" : servicePackageID,
            "optionalProductList" : optionalProductList,
            "subscriptionDate" : subscriptionDate
        }
        form = JSON.stringify(form);
        var req = new XMLHttpRequest();
        req.open("POST", "GoToBuyServices");
        req.setRequestHeader("Content-Type", "application/json");
        req.onreadystatechange = function (){
            if(req.readyState === XMLHttpRequest.DONE)
            {
                window.location.href = "/db2_project_war_exploded/GoToConfirmationPage?orderID=0";
            }
        };
        req.send(form);
    }
}

function enableCheckboxes(selected){
    document.querySelectorAll("input[type = 'checkbox']").forEach(value => value.checked = false);
    document.querySelectorAll("input[type = 'checkbox']").forEach(value => value.disabled = true);

    var tableRow = selected.closest("tr");
    tableRow.querySelectorAll("input[type = 'checkbox']").forEach(value => value.disabled = false);
}