let Pid=parseInt(sessionStorage.getItem("Pid"))
if(!(Pid)) window.location.href="login.html";

let Cid = sessionStorage.getItem("Cid")
fetch(`http://localhost:8010/character/view/${Cid}`)
               .then(
                     function(response) {
                     if (response.status!==200) {
                     console.log("There was a problem, status code " + response.status);
                     return;
                     }
                     response.json().then(function(data) {
                         console.log(data);
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
            if (data.length > 0) {
                document.getElementById("spellshead").innerHTML="Character Spells"
                document.getElementById("spelltablespace").innerHTML=`<table id="spellstable" class="table table-striped" width="100%">
                <thead>
                  <tr>
                    <th scope="col">Spell Name</th>
                    <th scope="col">Mana Cost</th>
                    <th scope="col">Range</th>
                    <th scope="col">Description</th>
                  </tr>
                </thead>
                <tbody id="spelltable">
                  
                </tbody>
                </table>`
            }
            for (a=0; a < data.length; a++) {
                document.getElementById("spelltable").innerHTML+=`<tr><th scope="row" class="tabletxt">${data[a].spell_name}</th>
                <td class="tabletxt">${data[a].mana_cost}</td>
                <td class="tabletxt">${data[a].type}</td>
                <td class="tabletxt">${data[a].description}</td></tr>`
            }
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
            window.location.href="Account.html"
        }).catch(function(error) {
            console.log("Request failed", error);
        })      
    }
})
document.querySelector("#buyskillbutton").addEventListener("click", function(a) {
    window.location.href="spendXP.html"
})

document.querySelector("#resetXPbutton").addEventListener("click", () => {
     let reset = window.confirm("This will delete all of your character skills and reset your XP. Are you sure you wish to proceed?")
     if(reset != true) {console.log(reset);}
     else {ResetXP()}
})
function ResetXP() {
   
    fetch(`http://localhost:8010/charskills/reset/${Cid}`, {
        method: "DELETE",
        mode: "cors" }
        )
        .then(response => response)
        .then(function(data) {
            console.log("Request succeeded with JSON response",data);
        }).then(resetSpells())
        .catch(function(error) {
            console.log("Request failed", error);
        })
}
function resetSpells() {
    fetch(`http://localhost:8010/charspell/reset/${Cid}`, {
        method: "DELETE",
        mode: "cors" }
        )
        .then(response => response)
        .then(function(data) {
            console.log("Request succeeded with JSON response",data);
        }).then(resetChar())
        .catch(function(error) {
            console.log("Request failed", error);
        })
}
function resetChar() {
    const newChar = {
        "char_id": Cid,
        "hp": 3,
        "mp": 3,
        "ap_basic": 1,
        "xp_spent": 0,
        "ap_light": 0,
        "ap_heavy": 0

    }
    fetch(`http://localhost:8010/character/update/${Cid}`, {
          method: "POST",
          mode: "cors",
          headers: {
              "Content-Type": "application/json",
          },
          body: JSON.stringify(newChar)
      }).then(response => response)
      .then(function (data) {
          console.log("Request succeeded with JSON response",data);
          location.reload()
      }).catch(function(error) {
          console.log("Request failed", error);
      })
  }
  document.querySelector("#backtoaccount").addEventListener("click", (a) => {
    a.preventDefault
    window.location.href="Account.html"
})
