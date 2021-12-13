function enableAllowedOptionalProducts(){
    var validityPeriod = document.getElementById("validityPeriod").value;
    var optionalProductsTableRows = document.getElementsByClassName("tr2");
    Array.from(optionalProductsTableRows).forEach((tableRow)=>{
        tableRow.style.display = "block";
        if(tableRow.querySelector("span").textContent !== validityPeriod){
            tableRow.style.display = "none";
        }
    })
}

function clearOptionalProductsShown(){
    Array.from(document.getElementsByClassName("tr2")).forEach((tableRow)=>{
        tableRow.style.display = "none";
    })
}