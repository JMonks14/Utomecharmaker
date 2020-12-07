function findbyUsername(Username) {
fetch(`http://localhost:8010/player/find/${Username}`)
.then(
    function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
        }
        response.json().then(function(data) {
            // console.log(data);
            let id = data.player_id
            let name = data.first_name + " " + data.last_name
            let username = data.username
            sessionStorage.setItem("Pid",id)

            document.getElementById("idpara").innerHTML+=id;
            document.getElementById("namepara").innerHTML+=name;
            document.getElementById("usernamepara").innerHTML+=username;
            
            let role = data.role
            if (role.indexOf("ROLE_ADMIN") != -1) {
                document.getElementById("adminbuttspace").innerHTML+=`<button id="adminbutton" type="button" class="btn btn-primary">Admin Console</button>`
                document.querySelector("#adminbutton").addEventListener("click", function(a) {
                a.preventDefault()
                window.location.href="admin"
                })
            }

            

            addQuerySelectors(data)
            // console.log("====================");
            // console.log(player);
            // console.log("====================");
            //brings up character name or create character button if none exists
            if (data.activeChar===0) {
                document.getElementById("charnamespace").innerHTML+="You do not have an active character at present.";
            document.getElementById("charcbuttspace").innerHTML+=`<button id="createcharbutton" type="button" class="btn btn-primary">Create Character</button>`;
            document.querySelector("#createcharbutton").addEventListener("click", function(a) {
            a.preventDefault()
            window.location.href="CreateChar"
            })
            } else {
    
            fetch(`http://localhost:8010/character/view/${data.activeChar}`)
               .then(
                     function(response) {
                     if (response.status!==200) {
                     console.log("There was a problem, status code " + response.status);
                     return;
                     }
                     response.json().then(function(data) {
                     name = data.char_name
                     Cid = data.char_id
                     sessionStorage.setItem("Cid", Cid)

            
                    // console.log(name);            
                    document.getElementById("charnamespace").innerHTML+=name;
                    document.getElementById("charcbuttspace").innerHTML+=`<button id="viewcharbutton" type="button" class="btn btn-primary">View Character</button>`;
                    // document.getElementById("rcharbuttspace").innerHTML+=`<button id="retirecharbutton" type="button" class="btn btn-primary">Retire Character</button>`;
                    document.querySelector("#viewcharbutton").addEventListener("click", function(a) {
                        a.preventDefault()
                        window.location.href="viewchar"
                        })
                 })}).catch(function(error) {
                    console.log("Request failed", error);
                })
                }})}).catch(function(error) {
                    console.log("Request failed", error);
                })
                }
function addQuerySelectors(data){
    let player = data
//brings up name update form when button clicked
document.querySelector("#updatenamebutton").addEventListener("click", function(upName) {
    upName.preventDefault();
    document.getElementById("updatenamebutton").remove();
    document.getElementById("namepara").remove();
    document.getElementById("changepasswordbutton").remove();
    document.getElementById("updateusernamebutton").remove()

    let nameform = `<form id="nameupdate">
    <div class="form-group">
      <label for="firstnameupdate">First Name</label>
      <input type="text" class="form-control" id="firstnameupdate">
    </div>
    <div class="form-group">
      <label for="lastnameupdate">Last Name</label>
      <input type="text" class="form-control" id="lastnameupdate">
    </div>
    <div class="form-group">
      <label for="passwordconfirm">Please confirm your current password to update your account</label>
      <input type="password" class="form-control" id="passwordconfirm">
    </div>
    <button id="subnamebutton" type="submit" class="btn btn-primary">Submit</button>
    </form> <br> <button id="cancel" class="btn btn-primary">Cancel</button>`
    
    document.getElementById("nameformspace").innerHTML+=nameform;

    document.querySelector("#cancel").addEventListener("click", (c) => {
        c.preventDefault()
        location.reload()
    })
    
    //sends new info when submitted
    document.querySelector("#nameupdate").addEventListener("submit", function(e) {
        e.preventDefault();
        let n = document.querySelector("#nameupdate").elements;

        let nfname = n["firstnameupdate"].value;
        let nlname = n["lastnameupdate"].value;
        let password = n["passwordconfirm"].value;

        if(nfname != player.first_name && nfname != "") {
            player.first_name = nfname
        }
        if(nlname !=player.last_name && nlname != "") {
            player.last_name = nlname
        }

        playernameUp(player, password)
    })
    
})
//function which sends update info

//brings up username update form when clicked
document.querySelector("#updateusernamebutton").addEventListener("click", function(upName) {
    upName.preventDefault();
    document.getElementById("updateusernamebutton").remove();
    document.getElementById("usernamepara").remove();
    document.getElementById("updatenamebutton").remove()
    document.getElementById("changepasswordbutton").remove();
    
    let usernameform = `<br><form id="usernameupdate">
    <div class="form-group">
      <label for="unameupdate">Username</label>
      <input type="text" class="form-control" id="unameupdate">
    </div>
    <div class="form-group">
      <label for="passwordconfirm">Please confirm your current password to update your account</label>
      <input type="password" class="form-control" id="passwordconfirm">
    </div>
    <button id="subusernamebutton" type="submit" class="btn btn-primary">Submit</button>
    </form> <br> <button id="cancel" class="btn btn-primary">Cancel</button>`
    
    document.getElementById("usernameformspace").innerHTML+=usernameform;

    document.querySelector("#cancel").addEventListener("click", (c) => {
        c.preventDefault()
        location.reload()
    })

    document.querySelector("#usernameupdate").addEventListener("submit", function(f) {
        f.preventDefault();
        let n = document.querySelector("#usernameupdate").elements;

        let nuname = n["unameupdate"].value;
        let password = n["passwordconfirm"].value;

        if(nuname != player.username && nuname != "") {
            player.username = nuname
        }
        
        playernameUp(player, password)
        // location.reload()
    })
})

//brings up name update form when button clicked
document.querySelector("#changepasswordbutton").addEventListener("click", function(upName) {
    upName.preventDefault();
    document.getElementById("changepasswordbutton").remove();
    document.getElementById("updatenamebutton").remove()
    document.getElementById("updateusernamebutton").remove()
    let passform = `<form id="passwordupdate">
    <div class="form-group">
      <label for="passwordconfirm">Please confirm your current password to update your account</label>
      <input type="password" class="form-control" id="passwordconfirm">
    </div>
    <div class="form-group">
      <label for="newpassord">New Password:</label>
      <input type="password" class="form-control" id="newpassword">
    </div>
    <div class="form-group">
      <label for="newpassordcon">Confirm New Password:</label>
      <input type="password" class="form-control" id="newpasswordcon">
    </div>
    <button id="subnamebutton" type="submit" class="btn btn-primary">Submit</button> 
    </form> <br> <button id="cancel" class="btn btn-primary">Cancel</button>`
    
    document.getElementById("passformspace").innerHTML+=passform;
    
    document.querySelector("#cancel").addEventListener("click", (c) => {
        c.preventDefault()
        location.reload()
    })
    
    //sends new info when submitted
    document.querySelector("#passwordupdate").addEventListener("submit", function(e) {
        e.preventDefault();
        let p = document.querySelector("#passwordupdate").elements;

        let oldpass = p["passwordconfirm"].value;
        let newpass = p["newpassword"].value;
        let newpasscon = p["newpasswordcon"].value;

         if (newpass != newpasscon) {
            window.alert("Update failed: New passwords do not match")
        } else if (newpass === "") {
            window.alert("Update failed: New password must contain characters")
        } else {
            player.password = newpass
            passwordUp(player, oldpass)
        }
    })
})

function playernameUp(data, passcheck) {
    let csrfToken = $("meta[name='_csrf']").attr("content")

    fetch(`http://localhost:8010/player/update/${sessionStorage.getItem("Pid")}`, {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrfToken,
            "passcheck": passcheck
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status==202) {
            window.alert("Account update successful")
            location.reload()
        } else if (response.status == 401) {
            window.alert("Unable to update account as the current password you entered was incorrect")
        } else if (response.status = 500) {
            window.alert("The username you entered cannot be chosen as it is already in use")
        }
    })
    .then(function (data) {
        console.log("Request succeeded with JSON response",data);
//        location.reload()
    }).catch(function(error) {
        console.log("Request failed", error);
    })}}

function passwordUp(data, passcheck) {
    let csrfToken = $("meta[name='_csrf']").attr("content")

    fetch(`http://localhost:8010/player/updatePassword/${sessionStorage.getItem("Pid")}`, {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrfToken,
            "passcheck": passcheck
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status==202) {
            window.alert("Account update successful")
            location.reload()
        } else if(response.status == 401) {
            window.alert("Unable to update account as the current password you entered was incorrect")
        }}
    )
    .then(function (data) {
        console.log("Request succeeded with JSON response",data);
//        location.reload()
    }).catch(function(error) {
        console.log("Request failed", error);
 })}

fetch(`http://localhost:8010/player/current`)
.then(
    function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
        }
        response.text().then(function(data) {
            findbyUsername(data);
        }).catch(function(error) {
            console.log("Request failed", error);
    })
        }).catch(function(error) {
            console.log("Request failed", error);
    })
