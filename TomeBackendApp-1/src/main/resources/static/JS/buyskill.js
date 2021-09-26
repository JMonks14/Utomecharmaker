// let Pid=parseInt(sessionStorage.getItem("Pid"))
// if(!(Pid)) window.location.href="login.html";

document.querySelector("#backtocharview").addEventListener("click", (a) => {
  a.preventDefault
  window.location.href="viewchar"
})

document.querySelector("#armourdrop").addEventListener("click", function(a) {
    a.preventDefault()
    //loads tree picture
    document.getElementById("treehead").innerHTML="Armour Tree"
    document.getElementById("picspace").innerHTML='<img alt="Armour Tree" src="images/armourtree.png" width=100%>'
    //retrieve list of skills from the database
    getSkills(1)
    })

document.querySelector("#bodydrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Body Tree"
    document.getElementById("picspace").innerHTML='<img alt="Body Tree" src="images/bodytree.png" width=100%>'
    getSkills(2)
})
document.querySelector("#resdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Resistance Tree"
    document.getElementById("picspace").innerHTML='<img alt="Resistance Tree" src="images/resistancetree.png" width=100%>'
    getSkills(3)
})
document.querySelector("#magicdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Magic Tree"
    document.getElementById("picspace").innerHTML='<img alt="Magic Tree" src="images/magictree.png" width=100%>'
    getSkills(4)
})
document.querySelector("#weapondrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Weapon Tree"
    document.getElementById("picspace").innerHTML='<img alt="Weapon Tree" src="images/weapontree.png" width=100%>'
    getSkills(5)
})
//loads skills in chosen tree
function getSkills(tree_id) {
fetch(`https://localhost:8010/skills/listbytree/${tree_id}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("manacosthead").innerHTML=""
              document.getElementById("manacostpara").innerHTML=""
              document.getElementById("rangehead").innerHTML=""
              document.getElementById("rangepara").innerHTML=""
              document.getElementById("skillnamehead").innerHTML=""
            document.getElementById("skillnamepara").innerHTML= ""
            document.getElementById("descripheading").innerHTML=""
            document.getElementById("skillbuydescription").innerHTML=""
            document.getElementById("spelldrophead").innerHTML=""
              document.getElementById("spellform").innerHTML=""
          document.getElementById("skilldrophead").innerHTML="Choose A Skill"
          document.getElementById("skillform").innerHTML=`<button id="chooseskillbutt" type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" width="50%">
          Skills
          </button>
          <div id ="chooseskill" class="dropdown-menu" aria-labelledby="dropdownMenu2"></div>`  
          // Examine the text in the response
          response.json().then(function(data) {
            // console.log(data);
            getCharSkills(data)});
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
}
//filters skills buy which ones the player's character has available
function getCharSkills(inskills) {
  let skills=inskills
    fetch(`https://localhost:8010/character/view/${sessionStorage.getItem("Cid")}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }                       
          response.json().then(function(data) {
            // console.log(data);
            let char_skills=data.skills                      
            let char_skillIds =[]
            
            
          for (let x=0; x < char_skills.length; x++){
            let char_skillId=char_skills[x].skill_id
            // console.log(char_skillId);
            char_skillIds.push(char_skillId)
          }
          // console.log(char_skillIds);
          // console.log(skills);
            for (let i =0; i<skills.length;i++) {
              let skill=skills[i]
              if(skill.prerequisite_1!=0) {
                if(!(char_skillIds.includes(skill.prerequisite_1) || char_skillIds.includes(skill.prerequisite_2) || char_skillIds.includes(skill.prerequisite_3) || char_skillIds.includes(skill.prerequisite_4) || char_skillIds.includes(skill.prerequisite_5))) {
                  continue
                }
              }
              if (char_skillIds.includes(skill.skill_id) && skill.is_multibuy===false) continue;

              let formoption = `<button id="skillget_${skills[i].skill_id}" onclick="loadSkill(${skills[i].skill_id})" class="dropdown-item" type="button">${skills[i].skill_name}</button>`
              document.getElementById("chooseskill").innerHTML+=formoption;
            }              
          })                     
        }).catch(function(err) {
          console.log('Fetch Error :-S', err);         
       });       
      }
//loads information about a skill when a player selects it 
function loadSkill(n) {
  fetch(`https://localhost:8010/skills/findbyid/${n}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          response.json().then(function(data) {
            console.log(data)
            document.getElementById("skillnamehead").innerHTML="Skill Name:"
            document.getElementById("skillnamepara").innerHTML= data.skill_name
            document.getElementById("descripheading").innerHTML="Skill Description:"
            document.getElementById("skillbuydescription").innerHTML=data.description
            if (n===31) {
              document.getElementById("buybuttspace").innerHTML=``
              getSpells()
            } else {
              document.getElementById("manacosthead").innerHTML=""
              document.getElementById("manacostpara").innerHTML=""
              document.getElementById("rangehead").innerHTML=""
              document.getElementById("rangepara").innerHTML=""
              document.getElementById("spelldrophead").innerHTML=""
              document.getElementById("spellform").innerHTML=""
              //adds confirm button
              document.getElementById("buybuttspace").innerHTML=`<button id="confirmbuy" type="button" class="btn btn-primary">Confirm Skill Purchase</button>`
              document.querySelector("#confirmbuy").addEventListener("click",function(b) {
                b.preventDefault
                buySkill(data)                            
              })
            }
         })  
})}

//retrieves spells from database when user selects cantrips
function getSpells() { 
  fetch(`https://localhost:8010/spell/listall`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("spelldrophead").innerHTML="Choose A Spell"
          document.getElementById("spellform").innerHTML=`<button id="choosespellbutt" type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" width="50%">
          Spell
          </button>
          <div id ="choosespell" class="dropdown-menu" aria-labelledby="dropdownMenu2"></div>`

          response.json().then(function(data) {getCharSpells(data)});

}).catch(function(err) {
  console.log('Fetch Error :-S', err);
});}
//filters spells to display only those available to the player
function getCharSpells(inspells) {
  let spells=inspells
    fetch(`https://localhost:8010/character/view/${sessionStorage.getItem("Cid")}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }                       
          response.json().then(function(data) {
            // console.log(data);
            let char_spells=[]                      
            // console.log(data);
            let charSpells=data.spells
                      
          for (let x=0; x < charSpells.length; x++){
            let char_spell=charSpells[x].spell_id
            // console.log(char_spell);
            char_spells.push(char_spell)
          }
          // console.log(char_spells);
          // console.log(skills);
            for (let i =0; i<spells.length;i++) {
              let spell=spells[i]
              if(spell.prerequisite!=0) {
                if(!(char_spells.includes(spell.prerequisite))) {
                  continue
                }
              }
              if (char_spells.includes(spell.spell_id)) continue;
              let formoption = `<button id="spellget_${spell.spell_id}" onclick="loadSpell(${spell.spell_id})" class="dropdown-item" type="button">${spell.spell_name}</button>`
              document.getElementById("choosespell").innerHTML+=formoption;
            }              
          })    
       }).catch(function(err) {
          console.log('Fetch Error :-S', err);         
       });
    }
function loadSpell(n) {
  fetch(`https://localhost:8010/spell/find/${n}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          response.json().then(function(data) {           
            document.getElementById("skillnamehead").innerHTML="Spell Name:"
            document.getElementById("skillnamepara").innerHTML= data.spell_name
            document.getElementById("descripheading").innerHTML="Spell Description:"
            document.getElementById("skillbuydescription").innerHTML=data.description
            document.getElementById("manacosthead").innerHTML="Mana Cost:"
            document.getElementById("manacostpara").innerHTML=data.mana_cost
            document.getElementById("rangehead").innerHTML="Range:"
            document.getElementById("rangepara").innerHTML=data.type
            if (data.spell_name==="Fling") {
              document.getElementById("skillbuydescription").innerHTML+=" You may also cast mass fling for 3 MP."
            }else if (data.spell_name==="Mute 30") {
              document.getElementById("skillbuydescription").innerHTML+=". You may also cast mass mute 30 for 2 MP."
            }
            document.getElementById("buybuttspace").innerHTML=`<button id="confirmspellbuy" type="button" class="btn btn-primary">Confirm Skill Purchase</button>`
              document.querySelector("#confirmspellbuy").addEventListener("click",function(b) {
                b.preventDefault
                buySpell(data)
              })
    })}).catch(function(err) {
      console.log('Fetch Error :-S', err);
    });}
    //calls the character from the database and updates the relevant fields
function buySkill(skill) {
  
      let n = skill.skill_id
      fetch(`https://localhost:8010/character/view/${sessionStorage.getItem("Cid")}`)
          .then(function(response) {
              if (response.status!==200) {
                  console.log("There was a problem, status code " + response.status);
                  return;
              }
              response.json().then(function(data) {
                let char = data
                char.skills.push(skill);
                char.hp = char.hp += skill.plusHP
                char.mp = char.mp += skill.plusFP
                char.ap_light = char.ap_light += skill.plusAPlight
                char.ap_heavy = char.ap_heavy += skill.plusAPheavy
                char.ap_magic = char.ap_magic += skill.plusAPmagic
                char.xp_spent = char.xp_spent += 1
                
                // console.log((char));
                updateChar(char)
                // console.log(data);                               
              })}).catch(function(error) {
                    console.log("Request failed", error);
                })
    }
    //saves spell as belonging to user's character
function buySpell(spell) {
      
  fetch(`https://localhost:8010/character/view/${sessionStorage.getItem("Cid")}`)
  .then(function(response) {
      if (response.status!==200) {
          console.log("There was a problem, status code " + response.status);
          return;
      }
      response.json().then(function(data) {
          let n=spell.spell_id
          let char = data
          char.spells.push(spell)
          
        if(n===6 || n ===9) {
          n++
          fetch(`https://localhost:8010/spell/find/${n}`)
          .then((response) => {
            if (response.status!==200) {
              console.log("There was a problem, status code " + response.status);
              return;
            }
            response.json().then(function(data){
              let mass =data
              char.spells.push(mass)
              // console.log(char);
              buyCantrips(char)
            }).catch(function(err) {
              console.log('Fetch Error :-S', err);
            })})
          } else {
            buyCantrips(char)
          } 
          
  })
})}
    //saves character back to database
function updateChar(char) {
  let csrfToken = $("meta[name='_csrf']").attr("content")
      fetch(`https://localhost:8010/character/update/${char.char_id}`, {
            method: "PUT",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": csrfToken
            },
            body: JSON.stringify(char)
        }).then(response => response)
        .then(function (data) {
            console.log("Request succeeded with JSON response",data);
            window.location.href="viewchar"
        })
        .catch(function(error) {
            console.log("Request failed", error);
        })
    }
function buyCantrips(char) {
  char.xp_spent++
  fetch(`https://localhost:8010/skills/findbyid/${31}`)
          .then(function(response) {
              if (response.status !== 200) {
                  console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                  return;
                }
                response.json().then(function(data) {
                  char.skills.push(data)
                  updateChar(char)
                })
                .catch(function(err) {
                  console.log('Fetch Error :-S', err);
                })   
        })   
      .catch(function(err) {
      console.log('Fetch Error :-S', err);
    });
}