// let Pid=parseInt(sessionStorage.getItem("Pid"))
// if(!(Pid)) window.location.href="login.html";

let Cid = sessionStorage.getItem("Cid")
fetch(`http://localhost:8010/character/view/${Cid}`)
               .then(
                     function(response) {
                     if (response.status!==200) {
                     console.log("There was a problem, status code " + response.status);
                     return;
                     }
                     response.json().then(function(data) {
                        //  console.log(data);
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

                        if (data.spells.length > 0) {
                            document.getElementById("btn-group").innerHTML=`<button type="button" id="dispskills" class="btn btn-success">View Skills</button>
                            <button type="button" id="dispspells" class="btn btn-primary">View Spells</button>`
                            document.getElementById("space").innerHTML="<br>"
                            
                            document.querySelector("#dispskills").addEventListener("click", () => {
                                dispSkills(data)
                            })
    
                            document.querySelector("#dispspells").addEventListener("click", () => {
                                dispSpells(data)
                            })
                        }

                        dispSkills(data)


                        document.querySelector("#resetXPbutton").addEventListener("click", () => {
                            let reset = window.confirm("This will delete all of your character skills and reset your XP. Are you sure you wish to proceed?")
                            if(reset != true) {console.log(reset);}
                            else {ResetXP(data)}
                        })
                        fetch("http://localhost:8010/headrefs/getmaxXP")
                        .then(function(response) {
                            if (response.status!==200) {
                            console.log("There was a problem, status code " + response.status);
                            return;
                            }
                            response.json().then(function(maxxp) {
                                // console.log(maxxp);
                                let xp_left = maxxp-data.xp_spent
                                if (xp_left ===0) {
                                    document.getElementById("buyskillbutton").remove()
                                }
                                document.getElementById("xppara").innerHTML+=xp_left
                            }

                            
                            )}).catch(function(error) {
                                console.log("Request failed", error);
                        })
                     
                 })}).catch(function(error) {
                    console.log("Request failed", error);
                })


document.querySelector("#retirecharbutton").addEventListener("click", function(retire) {
    retire.preventDefault()
    let conf = window.confirm("Your character will be considered dead and you will no longer be able to play them. Please click ok to confirm if you wish to proceed.")
    if(conf != true) {console.log(conf);}
    else {
        let csrfToken = $("meta[name='_csrf']").attr("content")
        fetch(`http://localhost:8010/character/kill/${Cid}`, {
        method: "PUT",
        mode: "cors",
        headers: {
            "X-CSRF-TOKEN": csrfToken
        }
    })
        .then(response => response)
        .then(function(data) {
            console.log("Request succeeded with JSON response",data);
            window.location.href="accounthome"
        }).catch(function(error) {
            console.log("Request failed", error);
        })      
    }
})
document.querySelector("#buyskillbutton").addEventListener("click", function(a) {
    a.preventDefault()
    window.location.href="buyskill"
})


function ResetXP(data) {
   let char = data
   console.log(char);
   char.skills = []
   char.spells = []
   char.hp = 3
   char.mp = 3
   char.ap_light = 0
   char.ap_heavy = 0
   char.ap_magic = 0
   char.xp_spent = 0
   resetChar(char)
}
function resetChar(char) {
    let newChar = char
    let csrfToken = $("meta[name='_csrf']").attr("content")
    fetch(`http://localhost:8010/character/update/${Cid}`, {
          method: "PUT",
          mode: "cors",
          headers: {
              "Content-Type": "application/json",
              "X-CSRF-TOKEN": csrfToken
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
    window.location.href="accounthome"
})

function dispSkills(data) {

    if (data.spells.length == 0) {
    document.getElementById("space").innerHTML=""
    }
    if (data.xp_spent ===0) {
        document.getElementById("msgspace").innerHTML = "You have not yet bought any skills."
    } else {
        document.getElementById("skilltablespace").innerHTML = `<table id="skillstable" class="table table-striped">
        <thead>
            <tr>
                <th scope="col">Skill Name</th>
                <th scope="col">Times Bought</th>
                <th scope="col">Description</th>
            </tr>
        </thead>
        <tbody id="skilltable">

        </tbody>
    </table>`
    }
    skillsArr=[]         
    for (i=0; i<data.skills.length; i++) {
     // console.log(data[i]);
        skillsArr.push({
            "name" : data.skills[i].skill_name,
            "times_bought": 1,
            "description": data.skills[i].description
        })                
    }
    // console.log(skillsArr);
    for (x=0;x<skillsArr.length;x++) {
    for (y=parseInt(x+1); y<skillsArr.length; y++) {

    if (skillsArr[x].name===skillsArr[y].name) {
        skillsArr[x].times_bought+=1
        skillsArr.splice(y,1)
        y=parseInt(x)
        }
    }
    }
    for (s in skillsArr) {
        document.getElementById("skilltable").innerHTML+=`<th scope="row" class="tabletxt">${skillsArr[s].name}</th>
        <td class="tabletxt">${skillsArr[s].times_bought}</td>
        <td class="tabletxt">${skillsArr[s].description}</td></tr>`
    }
}

function dispSpells(data) {
    if (data.spells.length > 0) {
        // document.getElementById("spellshead").innerHTML="Character Spells"
        document.getElementById("skilltablespace").innerHTML=`<table id="spellstable" class="table table-striped" width="100%">
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
    } else {
        document.getElementById("msgspace").innerHTML="Your character does not know any spells"
    }
    for (a=0; a < data.spells.length; a++) {
        document.getElementById("spelltable").innerHTML+=`<tr><th scope="row" class="tabletxt">${data.spells[a].spell_name}</th>
        <td class="tabletxt">${data.spells[a].mana_cost}</td>
        <td class="tabletxt">${data.spells[a].type}</td>
        <td class="tabletxt">${data.spells[a].description}</td></tr>`
    }
}