/* Coded with love by Igloo team. */
$(".toast").toast();

//url inicial
var server_url = 'http://localhost:8080/';

// actions for all tables

//////////////////////////////// Clean Tables /////////////////////////////////
function cleanTable(tableId){
  document.querySelector("#"+tableId+"").innerHTML = "";
}

///////////////////////////////////////////////////fill Tables //////////////////////////////////
                    //sector , "sector", "sectorTable", "Sector Name", 
function fillTable(settingName, settingStringName, tableId,settingHeadName ){

  var table = document.querySelector("#"+ tableId +"");
  
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
  thNameCol.innerHTML=settingHeadName;
  var thAction=document.createElement("th");
  thAction.innerHTML="Actions";
  
  
  //tableFirstRow.appendChild(thFirstColumn);
  tableFirstRow.appendChild(thRefCol);
  tableFirstRow.appendChild(thNameCol);
  tableFirstRow.appendChild(thAction);
  
  tableHeadContainer.appendChild(tableFirstRow);
  tableContainer.appendChild(tableHeadContainer);
  tableDivCOl.appendChild(tableContainer);
  table.appendChild(tableDivCOl);
  //table info 
  
  var tableBody=document.createElement("tbody");
  tableContainer.appendChild(tableBody);
  
  for (i in settingName){
  var tableBodyrow=document.createElement("tr");
  
  // CheckBOX column for next version improvements
  //var tableBodyCol1=document.createElement("td");
  //var col1Check=document.createElement("input");
  //col1Check.setAttribute("type", "checkbox");
  //tableBodyCol1.appendChild(col1Check);
  //tableBodyrow.appendChild(tableBodyCol1);
  
  var colRef=document.createElement("td");
  colRef.className="idcolumn";
  colRef.innerHTML="REF00" +settingName[i].id;
  tableBodyrow.appendChild(colRef);
  
  
  var colName=document.createElement("td");
  colName.className="nameColumn";
  colName.innerHTML=settingName[i].name;
  tableBodyrow.appendChild(colName);
  
  
  var colOptions=document.createElement("td");
  var colOptionsDiv=document.createElement("div");
  colOptionsDiv.className="btn-group";
  colOptionsDiv.setAttribute("role", "group");
  
  /* Favorites for Igloo next version
  var favbutton=document.createElement("button");
  favbutton.type="button";
  favbutton.className="btn btn-secondary";
  var favIcon=document.createElement("i");
  favIcon.className="far fa-star";
  favbutton.appendChild(favIcon); */
  
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
  
  //  colOptionsDiv.appendChild(favbutton);
  colOptionsDiv.appendChild(editbutton);
  colOptionsDiv.appendChild(deletebutton);
  
  colOptions.appendChild(colOptionsDiv);
  tableBodyrow.appendChild(colOptions);
  
  tableBody.appendChild(tableBodyrow);
  
  //Adding of Event Listeners to Options Buttons
  
  //favbutton.addEventListener("click", addSectorToFavorites());
  editbutton.addEventListener("click",function (event) { editItemForm(event, settingStringName, tableId, settingHeadName) });
  deletebutton.addEventListener("click",function (event) { deleteItem(event, settingStringName, tableId, settingHeadName) });
  }
  }
  
  /////////////////////////////////////// Delete Item from Table ////////////////////////////////

  function deleteItem(event, settingStringName, tableId, settingHeadName) {

    var rowToDelete=event.currentTarget.closest("tr");
    var columnIdToDelete=rowToDelete.querySelector(".idcolumn");
    var rawId=columnIdToDelete.innerHTML;
    var idtodelete=rawId.replace("REF00", "");
    
  
    var urlFinal = server_url + '/api/'+ settingStringName + '/delete?' + "id=" + idtodelete;
    console.log(urlFinal)
    fetch(urlFinal)
        .then(r => r.json())
        .then(ObjectToShow => {
            console.log(ObjectToShow);
            if (ObjectToShow.length==0)
                  {
                    $(document).ready(function () {
                      {
                        $("#deleteToastFail").toast("show");
                      }});
                  }
            else{
              cleanTable(tableId);
              fillTable(ObjectToShow,settingStringName, tableId, settingHeadName);
              $(document).ready(function () {
                {
                  $("#deleteToast").toast("show");
                }});

            }

        });
  }



//Edit Item from table
var urlToEdit="";
var tableIdGlobal="";
var settingNameGlobal="";
var settingHeadNameGlobal="";
function editItemForm(event,settingStringName, tableId, settingHeadName) {

  var rowToEdit=event.currentTarget.closest("tr");
  var columnIdToEdit=rowToEdit.querySelector(".idcolumn");
  var columnNameToEdit=rowToEdit.querySelector(".nameColumn")
  var rawId=columnIdToEdit.innerHTML;
  var rawName=columnNameToEdit.innerHTML;
  var idtoedit=rawId.replace("REF00", "");
  document.querySelector("#sectorForm").style.display="none";
  document.querySelector("#statusForm").style.display="none";
  document.querySelector("#paymentStatusForm").style.display="none";
  document.querySelector(".editForm").style.display="block";
  document.querySelector("#settingToEdit").value=rawName;
  
  //show edit Form  
  var urlFinal = server_url + '/api/'+ settingStringName +'/add?' + "id=" + idtoedit;
  urlToEdit=urlFinal;
  tableIdGlobal=tableId;
  settingNameGlobal=settingStringName;
  settingHeadNameGlobal=settingHeadName;
  console.log(urlToEdit)
}

function editItem(){
    console.log(urlToEdit)
    var newname=document.querySelector("#editName").value;
    urlToEdit=urlToEdit +"&name="+ newname;
    console.log(urlToEdit)
    fetch(urlToEdit)
    .then(r => r.json()) 
    .then(ObjectToShow => {
        cleanTable(tableIdGlobal);
        fillTable(ObjectToShow,settingNameGlobal, tableIdGlobal, settingHeadNameGlobal);
        $(document).ready(function () {
          {
            $("#editToast").toast("show");
          }});
    });
    cancelEdit();
    document.querySelector("#settingToEdit").value="";
  }



function cancelEdit(){
  document.querySelector(".editForm").style.display="none";      
}


//////////////////////////////// SECTOR SETTING OPTIONS ///////////////////////////////////
function showSectorON() {
  document.querySelector("#sectorForm").style.display = "";
  document.querySelector("#sectorTable").style.display = "";
  cancelEdit();
}
function showSectorOFF() {
  document.querySelector("#successAlertSector").style.display = "none";
  document.querySelector("#sectorForm").style.display = "none";
  document.querySelector("#sectorTable").style.display = "none";
  cancelEdit();
}

function showSector() {
  if (document.querySelector("#sectorForm").style.display == "none") {
    showSectorON();
    showStatusOFF();
    showPaymentStatusOFF();
    document.querySelector("#settingBgImage").style.display="none";
    
  } else {
    showSectorOFF();
    document.querySelector("#settingBgImage").style.display="";
  }
  cleanTable("sectorTable");
  showSectorsTable(); 
}

// Create New Sector
function createSector(){

var sectorName=document.querySelector("#sectorName").value;
var urlFinal= server_url + '/api/sector/add?'+"name="+ sectorName;

fetch(urlFinal)
        .then(r => r.json())
        .then(newsector => {
            console.log("Added sector: " + newsector.name);
            cleanTable("sectorTable");
            fillTable(newsector , "sector", "sectorTable", "Sector Name"); 
            $(document).ready(function () {
              {
                $("#createStatus").toast("show");
              }});
        });

document.querySelector("#sectorName").value = "";
//document.querySelector("#successAlertSector").style.display = "";        
document.querySelector("#sectorForm").style.display = "none";

}

// Show Sectors

function showSectorsTable() {

  fetch("http://localhost:8080/api/sector/get")
      .then(r => r.json())
      .then(sectors => {
          cleanTable("sectorTable");
          fillTable(sectors , "sector", "sectorTable", "Sector Name");
          
      });
}


///////////////////////////////////////////// STATUS SETTING OPTIONS ////////////////////////////////////////////////////////////////////////////

function showStatusON() {
  document.querySelector("#statusForm").style.display = "";
  document.querySelector("#statusTable").style.display = "";
  cancelEdit();
}
function showStatusOFF() {
  document.querySelector("#statusForm").style.display = "none";
  document.querySelector("#statusTable").style.display = "none";
  document.querySelector("#successAlertStatus").style.display = "none";
  cancelEdit();
}
function showStatus() {
  if (document.querySelector("#statusForm").style.display == "none") {
    showStatusON();
    showSectorOFF();
    showPaymentStatusOFF();
    document.querySelector("#settingBgImage").style.display="none";
  } else {
    showStatusOFF();
    document.querySelector("#settingBgImage").style.display="";
  }
  cleanTable("statusTable");
  showStatusTable();
}

// create New Status

function createStatus(){

  var statusName=document.querySelector("#statusName").value;
  var urlFinal= server_url + '/api/status/add?' + "name="+ statusName;
  
  fetch(urlFinal)
          .then(r => r.json())
          .then(newStatus => {
              console.log("Added status: " + newStatus.name);
              cleanTable("statusTable");
              fillTable(newStatus,"status","statusTable","Status Name" );
              $(document).ready(function () {
                {
                  $("#createStatus").toast("show");
                }});
          });


         
  
  document.querySelector("#statusName").value = "";
  //document.querySelector("#successAlertStatus").style.display = "";        
  document.querySelector("#statusForm").style.display = "none";  
  }


// Show Status

function showStatusTable() {

  fetch("http://localhost:8080/api/status/get")
      .then(r => r.json())
      .then(status => {
          cleanTable("statusTable");
          fillTable(status,"status","statusTable","Status Name" );
      });

}


//////////////////////////////////////////////////////// Payment Status /////////////////////////////////////////////////
function showPaymentStatusON() {
  document.querySelector("#paymentStatusForm").style.display = "";
  document.querySelector("#paymentStatusTable").style.display = "";
  cancelEdit();
}
function showPaymentStatusOFF() {
  document.querySelector("#successAlertPaymentStatus").style.display = "none";
  document.querySelector("#paymentStatusForm").style.display = "none";
  document.querySelector("#paymentStatusTable").style.display = "none";
  cancelEdit();
}

function showPaymentStatus() {
  if (document.querySelector("#paymentStatusForm").style.display == "none") {
    showPaymentStatusON();
    showSectorOFF();
    showStatusOFF();
    document.querySelector("#settingBgImage").style.display="none";
  } else {
    showPaymentStatusOFF();
    document.querySelector("#settingBgImage").style.display="";
  }
  cleanTable("paymentStatusTable");
  showPaymentStatusTable();
  

}

//create New Payment Status

function createPaymentStatus(){

  var paymentStatusName=document.querySelector("#paymentStatusName").value;
  var urlFinal= server_url + '/api/payment/add?'+"name="+ paymentStatusName;
  
  fetch(urlFinal)
          .then(r => r.json())
          .then(newpaymentStatus => {
              cleanTable("paymentStatusTable");
              fillTable(newpaymentStatus,"payment", "paymentStatusTable", "Payment Status Name");
              $(document).ready(function () {
                {
                  $("#createStatus").toast("show");
                }});
          
          
            });
  
  document.querySelector("#paymentStatusName").value = "";
  //document.querySelector("#successAlertPaymentStatus").style.display = "";  
  document.querySelector("#paymentStatusForm").style.display = "none";

        }


// Show Payment Status

function showPaymentStatusTable() {

  fetch("http://localhost:8080/api/payment/get")
      .then(r => r.json())
      .then(paymentstatuses => {
          cleanTable("paymentStatusTable");
          fillTable(paymentstatuses,"payment", "paymentStatusTable", "Payment Status Name");
      });
};
