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
                 })})