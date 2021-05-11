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

function showASectorsTable() {

  fetch("http://localhost:8080/api/sector/show")
      .then(r => r.json())
      .then(sectors => {
          cleanSectorTable();
          fillSectorTable(sectors);
      });
}


// Fill Sector Table 

function fillSectorTable(sectors){


var statusTable = document.querySelector("#statusTable");
document.querySelector("#statusTable").style.display="";


//Sector Table Container and Head

var tableDivCOl=document.createElement("div");
tableDivCOl.className="col-8 offset-2";

var tableContainer=document.createElement("table");
tableContainer.className="table table-hover";

var tableHeadContainer=document.createElement("thead");
tableHeadContainer.className="thead-dark";

var tableFirstRow=document.createElement("tr");
var thFirstColumn=document.createElement("th");
var thRefCol=document.createElement("th");
thRefCol.innerHTML="Ref";
var thNameCol=document.createElement("th");
thNameCol.innerHTML="Sector Name";
var thAction=document.createElement("th");
thAction.innerHTML="Actions";


tableFirstRow.appendChild(thFirstColumn);
tableFirstRow.appendChild(thRefCol);
tableFirstRow.appendChild(thNameCol);
tableFirstRow.appendChild(thAction);

tableHeadContainer.appendChild(tableFirstRow);
tableContainer.appendChild(tableHeadContainer);
tableDivCOl.appendChild(tableContainer);

//table info 

var tableBody=document.createElement("tbody");
tableContainer.appendChild(tableBody);

for (i in sectors){
var tableBodyrow=document.createElement(tr);

var tableBodyCol1=document.createElement(td);
var col1Check=document.createElement(input);
col1Check.setAttribute("type", "checkbox");
tableBodyCol1.appendChild(col1check);
tableBodyrow.appendChild(tableBodyCol1);

var colName=document.createElement(td);
colName.innerHTML=sectors[i].name;
tableBodyrow.appendChild(colName);


var colOptions=document.createElement(td);
var colOptionsDiv=document.createElement(div);
colOptionsDiv.className="btn-group";
colOptionsDiv.setAttribute("role", "group");

var favbutton=document.createElement(button);
favbutton.type="button";
favbutton.className="btn btn-secondary";

var favIcon=document.createElement(i);
favIcon.className="far fa-star";

favbutton.appendChild(favIcon);

var editbutton=document.createElement(button);
editbutton.type="button";
editbutton.className="btn btn-secondary";
var editIcon=document.createElement(i);
editIcon.className="fas fa-edit";
editbutton.appendChild(editIcon);

var deletebutton=document.createElement(button);
deletebutton.type="button";
deletebutton.className="btn btn-secondary";
var deleteIcon=document.createElement(i);
deleteIcon.className="fas fa-trash-alt";
deletebutton.appendChild(deleteIcon);

colOptionsDiv.appendChild(favbutton);
colOptionsDiv.appendChild(editbutton);
colOptionsDiv.appendChild(deletebutton);

colOptions.appendChild(colOptionDiv);
tableBodyrow.appendChild(colOptions);

}

}

//cleanSectorTable

function cleanSectorTable(){

  document.querySelector("#statusTable").innerHTML = "";


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