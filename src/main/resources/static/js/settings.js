//url inicial
var server_url = 'http://localhost:8080/';

// Sector
function showSectorON() {
  document.querySelector("#sectorForm").style.display = "";
  document.querySelector("#sectorTable").style.display = "";
}
function showSectorOFF() {
  document.querySelector("#successAlertSector").style.display = "none";
  document.querySelector("#sectorForm").style.display = "none";
  document.querySelector("#sectorTable").style.display = "none";
}

function showSector() {
  if (document.querySelector("#sectorForm").style.display == "none") {
    showSectorON();
    showStatusOFF();
    showPaymentStatusOFF();
  } else {
    showSectorOFF();
  }
  cleanSectorTable();
  showSectorsTable(); // AQUU EN REALIDAD LLAMARIAMOS A SHOW SECTOR TABLE
}

// Create New Sector
function createSector(){

var sectorName=document.querySelector("#sectorName").value;
var urlFinal= server_url + '/api/sector/add?'+"name="+ sectorName;

fetch(urlFinal)
        .then(r => r.json())
        .then(newsector => {
            console.log("Added sector: " + newsector.name);
        });

document.querySelector("#sectorName").value = "";
document.querySelector("#successAlertSector").style.display = "";        

}

// Show Sectors

function showSectorsTable() {

  fetch("http://localhost:8080/api/sector/show")
      .then(r => r.json())
      .then(sectors => {
          cleanSectorTable();
          fillSectorTable(sectors);
      });
}



// Fill Sector Table 

function fillSectorTable(sectors){


var statusTable = document.querySelector("#sectorTable");
//document.querySelector("#sectorTable").style.display="";


//Sector Table Container and Head

var tableDivCOl=document.createElement("div");
tableDivCOl.className="col-8 offset-2";

var tableContainer=document.createElement("table");
tableContainer.className="table table-hover";

var tableHeadContainer=document.createElement("thead");
tableHeadContainer.className="thead-dark";

var tableFirstRow=document.createElement("tr");
//var thFirstColumn=document.createElement("th");
var thRefCol=document.createElement("th");
thRefCol.innerHTML="Ref";
var thNameCol=document.createElement("th");
thNameCol.innerHTML="Sector Name";
var thAction=document.createElement("th");
thAction.innerHTML="Actions";


//tableFirstRow.appendChild(thFirstColumn);
tableFirstRow.appendChild(thRefCol);
tableFirstRow.appendChild(thNameCol);
tableFirstRow.appendChild(thAction);

tableHeadContainer.appendChild(tableFirstRow);
tableContainer.appendChild(tableHeadContainer);
tableDivCOl.appendChild(tableContainer);
statusTable.appendChild(tableDivCOl);
//table info 

var tableBody=document.createElement("tbody");
tableContainer.appendChild(tableBody);

for (i in sectors){
var tableBodyrow=document.createElement("tr");

// CheckBOX column for next version improvements

//var tableBodyCol1=document.createElement("td");
//var col1Check=document.createElement("input");
//col1Check.setAttribute("type", "checkbox");
//tableBodyCol1.appendChild(col1Check);
//tableBodyrow.appendChild(tableBodyCol1);

var colRef=document.createElement("td");
colRef.className="idcolumn";
colRef.innerHTML="REF00" +sectors[i].id;
tableBodyrow.appendChild(colRef);


var colName=document.createElement("td");
colName.innerHTML=sectors[i].name;
tableBodyrow.appendChild(colName);


var colOptions=document.createElement("td");
var colOptionsDiv=document.createElement("div");
colOptionsDiv.className="btn-group";
colOptionsDiv.setAttribute("role", "group");

var favbutton=document.createElement("button");
favbutton.type="button";
favbutton.className="btn btn-secondary";

var favIcon=document.createElement("i");
favIcon.className="far fa-star";

favbutton.appendChild(favIcon);

var editbutton=document.createElement("button");
editbutton.type="button";
editbutton.className="btn btn-secondary";
var editIcon=document.createElement("i");
editIcon.className="fas fa-edit";
editbutton.appendChild(editIcon);

var deletebutton=document.createElement("button");
deletebutton.type="button";
deletebutton.className="btn btn-secondary";
var deleteIcon=document.createElement("i");
deleteIcon.className="fas fa-trash-alt";
deletebutton.appendChild(deleteIcon);

colOptionsDiv.appendChild(favbutton);
colOptionsDiv.appendChild(editbutton);
colOptionsDiv.appendChild(deletebutton);

colOptions.appendChild(colOptionsDiv);
tableBodyrow.appendChild(colOptions);

tableBody.appendChild(tableBodyrow);

//Adding of Event Listeners to Options Buttons

favbutton.addEventListener("click", addSectorToFavorites());
editbutton.addEventListener("click",function (event) { editSector(event) });
deletebutton.addEventListener("click",function (event) { deleteSector(event) });
}
}

//cleanSectorTable

function cleanSectorTable(){

  document.querySelector("#sectorTable").innerHTML = "";

}

function addSectorToFavorites(){

    console.log("añadido a favoritos");
    
}

function editSector(){

  console.log("editado");
}

function deleteSector(){

  console.log("borrado");
}


//Delete sector

function deleteSector(event) {

  var rowToDelete=event.currentTarget.closest("tr");
  var columnIdToDelete=rowToDelete.querySelector(".idcolumn");
  var rawId=columnIdToDelete.innerHTML;
  var idtodelete=rawId.replace("REF00", "");
  

  var urlFinal = server_url + '/api/sector/delete?' + "idtodelete=" + idtodelete;

  fetch(urlFinal)
      .then(r => r.json())
      .then(sectors => {
          cleanSectorTable()
          fillSectorTable(sectors)
      });
}



function editSector(event) {

  var rowToEdit=event.currentTarget.closest("tr");
  var columnIdToEdit=rowToEdit.querySelector(".idcolumn");
  var rawId=columnIdToEdit.innerHTML;
  var idtoedit=rawId.replace("REF00", "");
  

  var urlFinal = server_url + '/api/sector/edit?' + "idtoedit=" + idtoedit;

  fetch(urlFinal)
      .then(r => r.json())
      .then(sectors => {
          cleanSectorTable()
          fillSectorTable(sectors)
      });
}





















// Status
function showStatusON() {
  document.querySelector("#statusForm").style.display = "";
  document.querySelector("#statusTable").style.display = "";
}
function showStatusOFF() {
  document.querySelector("#statusForm").style.display = "none";
  document.querySelector("#statusTable").style.display = "none";
  document.querySelector("#successAlertStatus").style.display = "none";
}
function showStatus() {
  if (document.querySelector("#statusForm").style.display == "none") {
    showStatusON();
    showSectorOFF();
    showPaymentStatusOFF();
  } else {
    showStatusOFF();
  }
}

// create New Status

function createStatus(){

  var statusName=document.querySelector("#statusName").value;
  var urlFinal= server_url + '/api/status/add?'+"name="+ name;
  
  fetch(urlFinal)
          .then(r => r.json())
          .then(newstatus => {
              console.log("Added status: " + newstatus.name);
          });
  
  document.querySelector("#statusName").value = "";
  document.querySelector("#successAlertStatus").style.display = "";        
  
  }













// Payment Status
function showPaymentStatusON() {
  document.querySelector("#paymentStatusForm").style.display = "";
  document.querySelector("#paymentStatusTable").style.display = "";
}
function showPaymentStatusOFF() {
  document.querySelector("#successAlertPaymentStatus").style.display = "none";
  document.querySelector("#paymentStatusForm").style.display = "none";
  document.querySelector("#paymentStatusTable").style.display = "none";
}
function showPaymentStatus() {
  if (document.querySelector("#paymentStatusForm").style.display == "none") {
    showPaymentStatusON();
    showSectorOFF();
    showStatusOFF();
  } else {
    showPaymentStatusOFF();
  }
}

//create New Payment Status

function createPaymentStatus(){

  var paymentStatusName=document.querySelector("#paymentStatusName").value;
  var urlFinal= server_url + '/api/status/add?'+"name="+ paymentStatusName;
  
  fetch(urlFinal)
          .then(r => r.json())
          .then(newpaymentStatus => {
              console.log("Added status: " + newpaymentStatus.name);
          });
  
  document.querySelector("#paymentStatusName").value = "";
  document.querySelector("#successAlertPaymentStatus").style.display = "";  

        }