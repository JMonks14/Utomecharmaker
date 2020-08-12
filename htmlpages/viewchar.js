let Cid = sessionStorage.getItem("Cid")
fetch(`http://localhost:8010/character/view/${Cid}`)
               .then(
                     function(response) {
                     if (response.status!==200) {
                     console.log("There was a problem, status code " + response.status);
                     return;
                     }
                     response.json().then(function(data) {
                        let name = data.char_name
                        document.getElementById("namespace").innerHTML+=name;
                        let bg = data.char_background
                        document.getElementById("charbackground").innerHTML+=bg;
                        console.log(data);
                        let statstring = `HP: ${data.hp} <br>
                        MP: ${data.mp} <br>
                        Max AP (basic): ${data.ap_basic} <br>`
                        if (data.ap_light > 0) {
                            statstring += `Max AP (light): ${data.ap_light} <br>`
                        }
                        if (data.ap_heavy > 0) {
                            statstring += `Max AP (heavy): ${data.ap_heavy} <br>`
                        }

                        document.getElementById("stats").innerHTML+=statstring

                        fetch("http://localhost:8010/headrefs/getmaxXP")
                        .then(function(response) {
                            if (response.status!==200) {
                            console.log("There was a problem, status code " + response.status);
                            return;
                            }
                            response.json().then(function(maxxp) {
                                console.log(maxxp);
                                document.getElementById("xppara").innerHTML+=maxxp-data.xp_spent
                            }
                     )})
                 })}).catch(function(error) {
                    console.log("Request failed", error);
                })
document.querySelector("#retirecharbutton").addEventListener("click", function(retire) {
    retire.preventDefault()
    let conf = window.confirm("Your character will be considered dead and you will no longer be able to play them. Please click ok to confirm if you wish to proceed.")
    if(conf != true) {console.log(conf);}
    else {
        fetch(`http://localhost:8010/character/kill/${Cid}`, {
        method: "POST",
        mode: "cors" }
        )
        .then(response => response)
        .then(function(data) {
            console.log("Request succeeded with JSON response",data);
        }).catch(function(error) {
            console.log("Request failed", error);
        })
        window.location.href="Account.html"
    }
})
document.querySelector("#buyskillbutton").addEventListener("click", function(a) {
    
    window.location.href="spendXP.html"
})