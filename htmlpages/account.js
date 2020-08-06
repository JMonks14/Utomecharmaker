let id =""
let name =""
let username=""
let player = {}

//fetches latest user info and writed to page
fetch("http://localhost:8010/player/viewlatest")
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
            player = data;
            // console.log("====================");
            // console.log(player);
            // console.log("====================");
        })
    }
)

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
    </form>`
    
    document.getElementById("nameformspace").innerHTML+=nameform;
    
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
        location.reload()

    })
    
})

function playernameUp(data) {

    fetch(`http://localhost:8010/player/update/${data.player_id}`, {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => response)
    .then(function (data) {
        console.log("Request succeeded with JSON response",data);
    }).catch(function(error) {
        console.log("Request failed", error);
    })
}

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
    </form>`

    document.getElementById("usernameformspace").innerHTML+=usernameform;

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
