function showDetails(trigger){
    var tr = trigger.closest("tr");
    var modalContainer = document.getElementById("modalContainer")
    var modal = document.getElementById("modal");

    // Reveal the modal

    modalContainer.style.display = "block";
    modal.style.display = "block";

    var type = tr.querySelector("td[id = 'type']");
    var minutes = tr.querySelector("td[id = 'minutes']");
    var sms = tr.querySelector("td[id = 'sms']");
    var fee_minutes = tr.querySelector("td[id = 'fee_minutes']");
    var fee_sms = tr.querySelector("td[id = 'fee_sms']");
    var gigabytes = tr.querySelector("td[id = 'gigabytes']");
    var fee_gigabytes = tr.querySelector("td[id = 'fee_gigabytes']");

    modal.innerHTML += "<h1> " + type.textContent + " details</h1>"

    if(minutes !== null){
        modal.innerHTML += "<div> Minutes:  " + minutes.textContent + "</div>"
    }

    if(sms !== null){
        modal.innerHTML += "<div> SMS: " + sms.textContent + "</div>"
    }

    if(fee_minutes !== null){
        modal.innerHTML += "<div> Fees for extra minutes:  " + fee_minutes.textContent + " &euro;</div>"
    }

    if(fee_sms !== null){
        modal.innerHTML += "<div> Fees for extra sms:  " + fee_sms.textContent + " &euro;</div>"
    }

    if(gigabytes !== null){
        modal.innerHTML += "<div> Gigabytes:  " + gigabytes.textContent + "</div>"
    }

    if(fee_gigabytes !== null){
        modal.innerHTML += "<div> Fees for extra gigabytes:  " + fee_gigabytes.textContent + " &euro;</div>"
    }

    // Attach close button
    document.getElementById("close").onclick = function() {
        modal.style.display = "none";
        modalContainer.style.display = "none";
        modal.innerHTML = "<button id='close' class='close'>x</button>";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            modalContainer.style.display = "none";
            modal.innerHTML = "<button id='close' class='close'>X</button>";
        }
    }
}