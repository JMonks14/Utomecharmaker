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
                        // console.log(data);
                        let statstring = `HP: ${data.hp} <br>
                        MP: ${data.mp} <br>
                        Max AP (basic): ${data.ap_basic} <br>`
                        if (data.ap_light > 0) {
                            statstring += `Max AP (light): ${data.ap_light} <br>`
                        }
                        if (data.ap_heavy > 0) {
                            statstring += `Max AP (heavy): ${data.ap_heavy} <br>`
                        }
                        if (data.ap_magic > 0) {
                            statstring += `Max AP (magic): ${data.ap_magic} <br>`
                        }

                        document.getElementById("stats").innerHTML+=statstring

                        if (data.xp_spent ===0) {
                            document.getElementById("skillstable").remove()
                            document.getElementById("msgspace").innerHTML = "You have not yet bought any skills."
                        }

                        fetch("http://localhost:8010/headrefs/getmaxXP")
                        .then(function(response) {
                            if (response.status!==200) {
                            console.log("There was a problem, status code " + response.status);
                            return;
                            }
                            response.json().then(function(maxxp) {
                                console.log(maxxp);
                                let xp_left = maxxp-data.xp_spent
                                if (xp_left ===0) {
                                    document.getElementById("buyskillbutton").remove()
                                }
                                document.getElementById("xppara").innerHTML+=xp_left
                            }
                     )})
                 })}).catch(function(error) {
                    console.log("Request failed", error);
                })
fetch(`http://localhost:8010/skills/findbychar/${Cid}`)
.then((response) => {
    if (response.status!==200) {
        console.log("There was a problem, status code " + response.status);
        return;
        }
        response.json().then(function(data) {
            console.log(data);
        
            skillsArr=[]
            
            for (i=0; i<data.length; i++) {
                
                // console.log(data[i]);
                
                    skillsArr.push({
                    "name" : data[i].skill_name,
                    "times_bought": 1,
                    "description": data[i].description
                })                
            }
            console.log(skillsArr);
            for (x=0;x<skillsArr.length;x++) {
                for (y=parseInt(x+1); y<skillsArr.length; y++) {
                    
                    if (skillsArr[x].name===skillsArr[y].name) {
                        skillsArr[x].times_bought+=1
                        skillsArr.splice(y,1)
                        y=parseInt(x+1)
                    }
                }
            }
            for (s in skillsArr) {
                document.getElementById("skilltable").innerHTML+=`<th scope="row" class="tabletxt">${skillsArr[s].name}</th>
            <td class="tabletxt">${skillsArr[s].times_bought}</td>
            <td class="tabletxt">${skillsArr[s].description}</td></tr>`
            }
            
        }).catch(function(error) {
            console.log("Request failed", error);
        })
})
fetch(`http://localhost:8010/spell/findbychar/${Cid}`)
.then((response) => {
    if (response.status!==200) {
        console.log("There was a problem, status code " + response.status);
        return;
        }
        response.json().then(function(data) {
            console.log(data);
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
