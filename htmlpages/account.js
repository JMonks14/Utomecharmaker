let id =""
let name =""
let username=""
var player = {
}
let Pid=parseInt(sessionStorage.getItem("Pid"))
if(!(Pid)) window.location.href="login.html";
//fetches user info and writes to page
fetch(`http://localhost:8010/player/view/${Pid}`)
.then(
    function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
        }
        response.json().then(function(data) {
            id = data.player_id
            name = data.first_name + " " + data.last_name
            username = data.username
            

            console.log(id);
            console.log(name);
            console.log(username);

            document.getElementById("idpara").innerHTML+=id;
            document.getElementById("namepara").innerHTML+=name;
            document.getElementById("usernamepara").innerHTML+=username;
            // console.log("====================");
            // console.log(player);
            // console.log("====================");
            //brings up character name or create character button if none exists
            if (data.activeChar===0) {
                document.getElementById("charnamespace").innerHTML+="You do not have an active character at present.";
            document.getElementById("charcbuttspace").innerHTML+=`<button id="createcharbutton" type="button" class="btn btn-primary">Create Character</button>`;
            document.querySelector("#createcharbutton").addEventListener("click", function(a) {
            a.preventDefault()
            window.location.href="Createchar.html"
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

            
                    console.log(name);            
                    document.getElementById("charnamespace").innerHTML+=name;
                    document.getElementById("charcbuttspace").innerHTML+=`<button id="viewcharbutton" type="button" class="btn btn-primary">View Character</button>`;
                    // document.getElementById("rcharbuttspace").innerHTML+=`<button id="retirecharbutton" type="button" class="btn btn-primary">Retire Character</button>`;
                    document.querySelector("#viewcharbutton").addEventListener("click", function(a) {
                        a.preventDefault()
                        window.location.href="viewchar.html"
                        })
                 })})}})})


//brings up name update form when button clicked
document.querySelector("#updatenamebutton").addEventListener("click", function(upName) {
    upName.preventDefault();
    document.getElementById("updatenamebutton").remove();
    document.getElementById("namepara").remove();

    let nameform = `<form id="nameupdate">
    <div class="form-group">
      <label for="firstnameupdate">First Name</label>
      <input type="text" class="form-control" id="firstnameupdate">
    </div>
    <div class="form-group">
      <label for="lastnameupdate">Last Name</label>
      <input type="text" class="form-control" id="lastnameupdate">
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

        if(nfname != player.first_name && nfname != "") {
            player.first_name = nfname
        }
        if(nlname !=player.last_name && nlname != "") {
            player.last_name = nlname
        }
        playernameUp(player)
    })
    
})
//function which sends update info
function playernameUp(data) {

    fetch(`http://localhost:8010/player/update/${Pid}`, {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => response)
    .then(function (data) {
        console.log("Request succeeded with JSON response",data);
        location.reload()
    }).catch(function(error) {
        console.log("Request failed", error);
    })
}
//brings up username update form when clicked
document.querySelector("#updateusernamebutton").addEventListener("click", function(upName) {
    upName.preventDefault();
    document.getElementById("updateusernamebutton").remove();
    document.getElementById("usernamepara").remove();

    let usernameform = `<br><form id="usernameupdate">
    <div class="form-group">
      <label for="unameupdate">Username</label>
      <input type="text" class="form-control" id="unameupdate">
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

        if(nuname != player.username && nuname != "") {
            player.username = nuname
        }
        playernameUp(player)
        location.reload()

})})

//brings up name update form when button clicked
document.querySelector("#changepasswordbutton").addEventListener("click", function(upName) {
    upName.preventDefault();
    document.getElementById("changepasswordbutton").remove();

    let passform = `<form id="passwordupdate">
    <div class="form-group">
      <label for="oldpassword">Enter Current Password:</label>
      <input type="password" class="form-control" id="oldpassword">
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

        let oldpass = p["oldpassword"].value;
        let newpass = p["newpassword"].value;
        let newpasscon = p["newpasswordcon"].value;

        if (oldpass != player.password) {
            window.alert("Update failed: Current password incorrect")
        } else if (newpass != newpasscon) {
            window.alert("Update failed: New passwords do not match")
        } else if (newpass === "") {
            window.alert("Update failed: New password must contain characters")
        } else {
            player.password = newpass
            playernameUp(player)
            window.alert("Password update successful.")
            location.reload()
        }
        
    
    })
    
})
document.querySelector("#logoutbutton").addEventListener("click", (o) => {
    o.preventDefault
    sessionStorage.clear()
    window.location.href="index.html"
})