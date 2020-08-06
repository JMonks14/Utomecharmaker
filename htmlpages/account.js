let id =""
let name =""
let username=""

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
      <input type="text" class="form-control" id="firstnameregister">
    </div>
    <div class="form-group">
      <label for="lastnameupdate">Last Name</label>
      <input type="text" class="form-control" id="lastnameregister">
    </div>
    <button id="subnamebutton" type="submit" class="btn btn-primary">Submit</button>
    </form>`
    
    document.getElementById("nameformspace").innerHTML+=nameform;
})

document.querySelector("#nameupdate").addEventListener("submit", function(e) {
    e.preventDefault();
    let n = document.querySelector("#nameupdate").elements;

    let nfname = n["firstnameupdate"].value
    let nlname = n["lastnameupdate"].value

    

})