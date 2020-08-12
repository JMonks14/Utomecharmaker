


document.querySelector("#armourdrop").addEventListener("click", function(a) {
    a.preventDefault()
    //loads tree picture
    document.getElementById("treehead").innerHTML="Armour Tree"
    document.getElementById("picspace").innerHTML="<img src='armourtree.png' width=100%>"
    //retrieve list of skills from the database
    getSkills(1)
    })

document.querySelector("#bodydrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Body Tree"
    document.getElementById("picspace").innerHTML="<img src='bodytree.png' width=100%>"
    getSkills(2)
})
document.querySelector("#resdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Resistance Tree"
    document.getElementById("picspace").innerHTML="<img src='resistancetree.png' width=100%>"
    getSkills(3)
})
document.querySelector("#magicdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Magic Tree"
    document.getElementById("picspace").innerHTML="<img src='magictree.png' width=100%>"
    getSkills(4)
})
document.querySelector("#weapondrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Weapon Tree"
    document.getElementById("picspace").innerHTML="<img src='weapontree.png' width=100%>"
    getSkills(5)
})
function getSkills(tree_id) {
fetch(`http://localhost:8010/skills/listbytree/${tree_id}`)
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
          response.json().then(function(data) {getCharSkills(data)});
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
}
function loadSkill(n) {
  fetch(`http://localhost:8010/skills/findbyid/${n}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          response.json().then(function(data) {
            
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
              document.getElementById("buybuttspace").innerHTML=`<button id="confirmbuy" type="button" class="btn btn-primary">Confirm Skill Purchase</button>`
              document.querySelector("#confirmbuy").addEventListener("click",function(b) {
                b.preventDefault
                buySkill(n)
                
                
              })
            }

          })  
})}
function getSpells() {
  
  fetch(`http://localhost:8010/spell/listall`)
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

          response.json().then(function(data) {
            console.log(data);
            for (let i =1; i<data.length;i++) {

              let formoption = `<button id="spellget_${data[i].spell_id}" onclick="loadSpell(${data[i].spell_id})" class="dropdown-item" type="button">${data[i].spell_name}</button>`
              document.getElementById("choosespell").innerHTML+=formoption;
            }            
          });

}).catch(function(err) {
  console.log('Fetch Error :-S', err);
});}
function loadSpell(n) {
  fetch(`http://localhost:8010/spell/find/${n}`)
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
    })}).catch(function(err) {
      console.log('Fetch Error :-S', err);
    });}
function getCharSkills(inskills) {
  let skills=inskills

    fetch(`http://localhost:8010/charskills/byid/${sessionStorage.getItem("Cid")}`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
                              
          
          response.json().then(function(data) {
            // console.log(data);
            let char_skills=[]                      
            console.log(data);
            
            console.log(skills);
          for (let x=0; x < data.length; x++){
            let char_skill=data[x].fk_skill_id
            console.log(char_skill);
            char_skills.push(char_skill)
          }
          console.log(char_skills);
          // console.log(skills);
            for (let i =0; i<skills.length;i++) {
              let skill=skills[i]
              if(skill.prerequisite_1!=0) {
                if(!(char_skills.includes(skill.prerequisite_1) || char_skills.includes(skill.prerequisite_2) || char_skills.includes(skill.prerequisite_3) || char_skills.includes(skill.prerequisite_4) || char_skills.includes(skill.prerequisite_5))) {
                  continue
                }
              }
              if (char_skills.includes(skill.skill_id) && skill.is_multibuy===false) continue;

              let formoption = `<button id="skillget_${skills[i].skill_id}" onclick="loadSkill(${skills[i].skill_id})" class="dropdown-item" type="button">${skills[i].skill_name}</button>`
              document.getElementById("chooseskill").innerHTML+=formoption;
            }    
          
          })
                  
           
        }).catch(function(err) {
          console.log('Fetch Error :-S', err);         
       });
         
      }
function buySkill(n) {
        const buy = {
          "fk_char_id": sessionStorage.getItem("Cid"),
          "fk_skill_id": n
        }
        fetch("http://localhost:8010/charskills/buy", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(buy)
        }).then(response => response)
        .then(function(data) {
        console.log("Request succeeded with JSON response",data);       
        })
        .then(getChar(n))
                  
        .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
      }
function getChar(n) {
  let Cid = sessionStorage.getItem("Cid")
  fetch(`http://localhost:8010/character/view/${Cid}`)
               .then(
                     function(response) {
                     if (response.status!==200) {
                     console.log("There was a problem, status code " + response.status);
                     return;
                     }
                     response.json().then(function(data) {
                       let char = data
                       console.log(char);
                       let APL2=[9,12]
                       let APL4=6
                       let APH2=[10,13]
                       let APH4=7
                       let HP=[15,17,19,21]
                       let MP1=[32,34,35]
                       let MP3=36
                       let APM2=[37,38]
                       char.xp_spent+=1
                       if (APL2.includes(n)) char.ap_light+=2;
                       if (APL4===n) char.ap_light+=4;
                       if (APH2.includes(n)) char.ap_heavy+=2;
                       if (APH4===n) char.ap_heavy+=8;
                       if (HP.includes(n)) char.hp+=1;
                       if (MP1.includes(n)) char.mp+=1;
                       if (MP3===n) char.mp+=3;
                       if (APM2.includes(n)) char.ap_magic+=2;
                       console.log((char));
                       updateChar(char)
                     }
                     
                     )})
                     .catch(function(err) {
                      console.log('Fetch Error :-S', err);
                    })
}
function updateChar(char) {
  fetch(`http://localhost:8010/character/update/${char.char_id}`, {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(char)
    }).then(response => response)
    .then(function (data) {
        console.log("Request succeeded with JSON response",data);
    })
    .then(window.location.href="viewchar.html")
    .catch(function(error) {
        console.log("Request failed", error);
    })
}