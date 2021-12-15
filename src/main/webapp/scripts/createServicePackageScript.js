var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
    // This function will display the specified tab of the form...
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    //... and fix the Previous/Next buttons:
    if (n == 0) {
        document.getElementById("prevBtn").style.display = "none";
        clearOptionalProductsShown();
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Submit";
        enableAllowedOptionalProducts();
    } else {
        document.getElementById("nextBtn").innerHTML = "Next";
    }
    //... and run a function that will display the correct step indicator:
    fixStepIndicator(n)
}

function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !validateForm()) return false;
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form...
    if (currentTab >= x.length) {
        // ... the form gets submitted:
        sendForm(document.getElementById("regForm"));
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
}

function validateForm() {
    // This function deals with validation of the form fields
    var x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[currentTab].getElementsByTagName("input");
    // A loop that checks every input field in the current tab:
    for (i = 0; i < y.length; i++) {
        // If a field is empty...
        if (y[i].value == "") {
            // add an "invalid" class to the field:
            y[i].className += " invalid";
            // and set the current valid status to false
            valid = false;
        }
    }
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
        document.getElementsByClassName("step")[currentTab].className += " finish";
    }
    return valid; // return the valid status
}

function fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class on the current step:
    x[n].className += " active";
}

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

function sendForm(form){
    if(form.checkValidity()) {
        var servicePackageName = form.querySelector("input[name = 'name']").value;
        var validityPeriod = form.querySelector("input[name = 'validityPeriod']").value;
        var monthlyFee = form.querySelector("input[name = 'monthlyFee']").value;

        var serviceList = Array.prototype.slice.call(form.querySelectorAll("input[name='serviceID']:checked"))
            .map(function (x){
                return x.value;
            });

        var optionalProductList = Array.prototype.slice.call(form.querySelectorAll("input[name='optionalProductID']:checked"))
            .map(function (x){
                return x.value;
            });

        form = {
            "servicePackageName" : servicePackageName,
            "validityPeriod" : validityPeriod,
            "monthlyFee" : monthlyFee,
            "serviceList" : serviceList,
            "optionalProductList" : optionalProductList
        }

        form = JSON.stringify(form);
        var req = new XMLHttpRequest();
        req.open("POST", "CreateServicePackage");
        req.setRequestHeader("Content-Type", "application/json");
        req.onreadystatechange = function (){
            if(req.readyState === XMLHttpRequest.DONE)
            {
                window.location.href = "/db2_project_war_exploded/GoToHomePageEmployee";
            }
        };
        req.send(form);
    }
}