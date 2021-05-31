fetch("https://localhost:8010/headrefs/getmaxXP")
    .then(function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
            }
        response.json().then(function(maxxp) {
            // console.log(maxxp);
            document.getElementById("maxXPdisp").innerHTML+=maxxp
        })                            
    }).catch(function(error) {
            console.log("Request failed", error);
    })

document.querySelector("#setmaxXP").addEventListener("click", (c) => {
    c.preventDefault()
    document.getElementById("setmaxXP").hidden=true
    document.getElementById("setMaxXPSpace").innerHTML=
    `<form id="setMaxXpForm">
        <div class="form-group" style="max-width: 100px">
            <label for="newMaxXP" >New Max XP</label>
            <input type="number" class="form-control" id="newMaxXP" required>
        </div>
        <input id="submitMaxXP" type="submit" class="btn btn-primary"></input>
    </form><br>
    <button id="cancel" class="btn btn-primary">Cancel</button>`

    document.querySelector("#cancel").addEventListener("click", (c) => {
        c.preventDefault()
        location.reload()
    })

    document.querySelector("#setMaxXpForm").addEventListener("submit", function(e) {
        e.preventDefault();
        let n = document.querySelector("#setMaxXpForm").elements;
        let newXP = n["newMaxXP"].value;
        let csrfToken = $("meta[name='_csrf']").attr("content")
        // console.log(newXP)
        fetch(`https://localhost:8010/headrefs/setmaxXP/${newXP}`, {
            method: "PUT",
            mode: "cors",
            headers: {
                "X-CSRF-TOKEN": csrfToken
            }
        }).then((response) => {
            console.log(response.status)
            location.reload()
        }).catch(function(error) {
            console.log("Request failed", error);
        })
    })
})

document.querySelector("#PidSearch").addEventListener("submit", (s) => {
    s.preventDefault()
    let elements = document.querySelector("#PidSearch").elements
    let Pid = elements["PidInput"].value

    playerIdSearch(Pid)
})

function playerIdSearch(Pid) {
    fetch(`https://localhost:8010/player/view/${Pid}`)
    .then((response) => {
        if (response.status === 404) {
            response.json().then((data) => {
                console.log(data.message)
                document.getElementById("PidSearchResult").innerHTML=data.message
            })
        }
        else if (response.status != 200) {
            console.log("looks like there was a problem " + response.status)
        }
        else {
            response.json().then((data) => {
            // console.log(data)
            let role=""
            if (data.role.indexOf("ROLE_ADMIN") != -1) role="Admin";
            else role="Player";
            let aCharId=""
            if (data.activechar_id ===0) aCharId="None";
            else aCharId=data.activechar_id
            document.getElementById("PidSearchResult").innerHTML=
            `<div class="card" style="width: 18rem;">
                <div class="card-body" id="cardbody">
                    <h5 class="card-title">${data.first_name} ${data.last_name}</h5>
                    <p class="card-text">Player ID: ${data.player_id}</p>
                    <p class="card-text">Email: ${data.email}</p>
                    <p class="card-text">Active Character Id: ${aCharId}</p>
                    <p class="card-text">Role: ${role}</p>
                </div>
            </div>`
            if (role == "Player") document.getElementById("cardbody").innerHTML+=`<button id="makeadmin" class="btn btn-primary" onclick="makeAdmin(${data.player_id})">Promote to Admin</button>`
            if (role == "Admin") document.getElementById("cardbody").innerHTML+=`<button id="demoteadmin" class="btn btn-primary"onclick="demoteAdmin(${data.player_id})">Remove Admin Status</button>`
            })
        }
    }).catch(function(error) {
        console.log("Request failed", error);
    })
}
function makeAdmin(n) {
    let conf = window.confirm("Please confirm that you are awarding admin status to the selected user.")
    if (conf) {
    let csrfToken = $("meta[name='_csrf']").attr("content")
    fetch(`https://localhost:8010/player/make_admin/${n}`, {
        method: "POST",
        mode: "cors",
        headers: {
            "X-CSRF-TOKEN": csrfToken
        },
        }).then(response => {
        // if (response.status===200) {
        //     window.alert(response)
        //     return;
        // }
        response.text().then((data) => {
            window.alert(data);
            document.getElementById("PidSearchResult").innerHTML=""
        })
    })
    .catch(function(error) {
        console.log("Request failed", error);
    })
}}

function demoteAdmin(n) {
    let conf = window.confirm("Please confirm that you are removing admin status from the selected user.")
    if (conf) {
        let csrfToken = $("meta[name='_csrf']").attr("content")
        fetch(`https://localhost:8010/player/make_notadmin/${n}`, {
            method: "DELETE",
            mode: "cors",
            headers: {
                "X-CSRF-TOKEN": csrfToken
            },
            }).then(response => {
            // if (response.status===200) {
            //     window.alert(response)
            //     return;
            // }
            response.text().then((data) => {
                window.alert(data);
                document.getElementById("PidSearchResult").innerHTML=""
            })
        })
        .catch(function(error) {
            console.log("Request failed", error);
        }) 
    }
   
}

document.querySelector("#listusers").addEventListener("click", (c) => {
    c.preventDefault()
    listAllUsers()
})
function listAllUsers(){
    fetch("https://localhost:8010/player/list_all")
    .then(function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
            }
        response.json().then(function(data) {
            // console.log(maxxp);
            // console.log(data)
            document.getElementById("PidSearchResult").innerHTML=`<table id="playerstable" class="table table-striped" width="100%">
            <thead>
              <tr>
                <th scope="col">Player ID</th>
                <th scope="col">Player Name</th>
                <th scope="col">Role</th>
                <th scope="col">Active character Id</th>
              </tr>
            </thead>
            <tbody id="playertable">              
            </tbody>
            </table>`
            for (a=0; a < data.length; a++) {
                let role = ""
                if (data[a].role.indexOf("ROLE_ADMIN") != -1) role = "Admin";
                else role = "Player";
                document.getElementById("playertable").innerHTML+=`<tr><th scope="row" class="tabletxt">${data[a].player_id}</th>
                <td class="tabletxt">${data[a].first_name} ${data[a].last_name}</td>
                <td class="tabletxt">${role}</td>
                <td class="tabletxt">${data[a].activechar_id}</td></tr>`
            }
        })                            
    }).catch(function(error) {
            console.log("Request failed", error);
    })
}

document.querySelector("#CidSearch").addEventListener("submit", (s) => {
    s.preventDefault()
    let elements = document.querySelector("#CidSearch").elements
    let Cid = elements["CidInput"].value

    charIdSearch(Cid)
})

function charIdSearch(Cid) {
    fetch(`https://localhost:8010/character/view/${Cid}`)
    .then((response) => {
        if (response.status === 404) {
            response.json().then((data) => {
                console.log(data.message)
                document.getElementById("CidSearchResult").innerHTML=data.message
            })
        }
        else if (response.status != 200) {
            console.log("looks like there was a problem " + response.status)
        }
        else {
            response.json().then((data) => {
            // console.log(data)
            let alive = ""
            if (data.alive ==true) alive="Alive";
            else alive = "Dead"

            skillsArr=[]         
            for (i=0; i<data.skills.length; i++) {
             // console.log(data[i]);
                skillsArr.push({
                    "name" : data.skills[i].skill_name,
                    "times_bought": 1,
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

            let skillstring = ""
            for (s in skillsArr) {
                skillstring+=skillsArr[s].name
                if (skillsArr[s].times_bought > 1) skillstring +=` (${skillsArr[s].times_bought})`;
                skillstring+=", "
            }
            // console.log(skillstring);
            let skillstrimmed = skillstring.slice(0,-2)
            // console.log(skillstrimmed);

            let spellstring = ""
            for (i = 0; i<data.spells.length; i++) {
                spellstring+= data.spells[i].spell_name
                spellstring+=", "
            }
            // console.log(spellstring);
            let spellstrimmed = spellstring.slice(0, -2)
            // console.log(spellstrimmed);

            document.getElementById("CidSearchResult").innerHTML=
            `<div class="card">
                <div class="card-body" id="cardbody">
                    <h5 class="card-title">${data.char_name}</h5>
                    <p class="card-text"><b>HP:</b> ${data.hp}, <b>AP (light):</b> ${data.ap_light}, <b>AP (heavy):</b> ${data.ap_heavy}, <b>AP (Magic):</b> ${data.ap_magic}, <b>MP:</b> ${data.mp}</p>
                    <p class="card-text"><b>CID:</b> ${data.char_id}, <b>Player ID:</b> ${data.player.player_id},          <b>Player Name:</b> ${data.player.first_name} ${data.player.last_name}</p>
                    <p class="card-text"><b>Race:</b> ${data.race},          <b>Origin:</b> ${data.origin}, <b>Status:</b> ${alive}</p>
                    <p class="card-text"><b>Skills:</b> ${skillstrimmed}</p>
                    <p class="card-text"><b>Spells:</b> ${spellstrimmed}</p>
                    <p class="card-text"><b>Background:</b></p>
                    <p class="card-text">${data.char_background}</p>
                </div>
            </div>`
            })
        }
    }).catch(function(error) {
        console.log("Request failed", error);
    })
}

document.querySelector("#listalive").addEventListener("click", (c) => {
    c.preventDefault()
    listAliveChars()
})
function listAliveChars(){
    fetch("https://localhost:8010/character/list_alive")
    .then(function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
            }
        response.json().then(function(data) {
            // console.log(maxxp);
            // console.log(data)
            document.getElementById("CidSearchResult").innerHTML=`<table id="charstable" class="table table-striped" width="100%">
            <thead>
              <tr>
                <th scope="col">CID</th>
                <th scope="col">Character Name</th>
                <th scope="col">Player Id</th>
                <th scope="col">Player Name</th>
              </tr>
            </thead>
            <tbody id="chartable">              
            </tbody>
            </table>`
            for (a=0; a < data.length; a++) {
                document.getElementById("chartable").innerHTML+=`<tr><th scope="row" class="tabletxt">${data[a].char_id}</th>
                <td class="tabletxt">${data[a].char_name}</td>
                <td class="tabletxt">${data[a].player.player_id}</td>
                <td class="tabletxt">${data[a].player.first_name} ${data[a].player.last_name}</td></tr>`
            }
        })                            
    }).catch(function(error) {
            console.log("Request failed", error);
    })
}