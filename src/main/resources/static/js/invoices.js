/* Coded with love by Igloo team. */
$(".toast").toast();
////////// Server URL //////////
var server_url = "http://localhost:8080";
var search_url = server_url + "/api/invoice/get?";
////////// Global VARs //////////
var currentPageGlobal = 1; // Current invoices page
var selectedInvoiceId; // Selected invoice
var selectedInvoicesIds = []; // Selected invoices array
var selectedId; // Used on edit method
////////// Selection ///////////
function addToSelected(event) {
  var row = event.currentTarget.closest("tr");
  var id = row.querySelector(".idIndicator").innerHTML;
  if (selectedInvoicesIds.includes(id)) {
    selectedInvoicesIds.splice(selectedInvoicesIds.indexOf(id), 1);
  } else {
    selectedInvoicesIds.push(id);
  }
  if (selectedInvoicesIds.length > 0) {
    document.querySelector("#batchDeleteButton").disabled = false;
    document.querySelector("#batchDeleteButton").className = "btn btn-add btn-lg btn-block filterText ";
  } else {
    document.querySelector("#batchDeleteButton").disabled = true;
    document.querySelector("#batchDeleteButton").className = "btn btn-del btn-lg btn-block filterText ";
  }
}
////////// Pagination //////////
function nextPage() {
  var finalRequest = "";
  currentPage = parseInt(currentPageGlobal);
  if (currentPage > 0) {
    var nextPage = currentPage + 1;
    if (search_url.indexOf("&") != -1) {
      search_url = search_url.split("&page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "&page=" + nextPage;
    } else {
      search_url = search_url.split("page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "page=" + nextPage;
    }
    fetchRequest(finalRequest, null);
    currentPageGlobal = nextPage;
    updatePages(nextPage);
  }
}
function prevPage() {
  var finalRequest = "";
  currentPage = parseInt(currentPageGlobal);
  if (currentPage > 0) {
    var prevPage = currentPage - 1;
    if (prevPage < 1) {
      var prevPage = currentPage;
    }
    if (search_url.indexOf("&") != -1) {
      search_url = search_url.split("&page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "&page=" + prevPage;
    } else {
      search_url = search_url.split("page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "page=" + prevPage;
    }
    fetchRequest(finalRequest, null);
    currentPageGlobal = prevPage;
    updatePages(prevPage);
  }
}

function updatePages(currentPage) {
  currentPageInt = parseInt(currentPage);
  var pageIndicators = document.querySelector("#paginationControls");
  pageIndicators.querySelector("#currentPageItem").innerHTML = currentPageInt;
  pageIndicators.querySelector("#currentPageItem").style.color = "#59bec9";
}
////////// Fetch //////////
function fetchRequest(finalRequest, toast, globalSearch) {
  if (globalSearch) {
    search_url = finalRequest;
    console.log("Search url:" + search_url);
  }
  console.log("Fetch request to: " + finalRequest);
  fetch(finalRequest)
    .then((r) => r.json())
    .then((invoices) => {
      cleanTable();
      fillTable(invoices);
      if (toast != null) {
        $(document).ready(function () {
          {
            $(toast).toast("show");
          }
        });
      }
    });
  resetSelectedList();
}
////////// Utilities //////////
function buttonFn(event, action, sortTerm, sortMethod, resetPage) {
  var aux = document.querySelectorAll(".lateralMenuAux");
  console.log(aux);
  for (var i = 0; i < aux.length; i++) {
    console.log(aux[i].style.color);
    aux[i].style.color = "rgb(255, 255, 255)";
  }

  if (event != null) {
    var rowButton = event.currentTarget.closest(".row");
    var ico = rowButton.querySelector("i");
    var lab = rowButton.querySelector("label");
    if (ico.style.color == "rgb(89, 190, 201)" && lab.style.color == "rgb(89, 190, 201)") {
    } else if (ico.style.color == "rgb(255, 255, 255)" && lab.style.color == "rgb(255, 255, 255)") {
      ico.style.color = "rgb(89, 190, 201)";
      lab.style.color = "rgb(89, 190, 201)";
    }
  }

  getInvoices(action, sortTerm, sortMethod, resetPage);
}
function resetSelectedList() {
  selectedInvoicesIds = [];
}
function unHideCreationForm() {
  document.querySelector("#creationForm").style.display = "";
}
function hideCreationForm() {
  document.querySelector("#creationForm").style.display = "none";
}
function unHideEditForm() {
  document.querySelector("#editForm").style.display = "";
}
function hideEditForm() {
  document.querySelector("#editForm").style.display = "none";
}
function clearEditForm() {
  document.querySelector("input[name=preTaxEdited]").value = "";
  document.querySelector("select[name=clientIdEdited]").value = "1";
  document.querySelector("select[name=statusIdEdited]").value = "1";
  document.querySelector("select[name=paymentStatusIdEdited]").value = "1";
  document.querySelector("select[name=sectorIdEdited]").value = "1";
}
function clearCreationForm() {
  document.querySelector("input[name=preTax]").value = "";
  document.querySelector("select[name=clientId]").value = "1";
  document.querySelector("select[name=statusId]").value = "1";
  document.querySelector("select[name=paymentStatusId]").value = "1";
  document.querySelector("select[name=sectorId]").value = "1";
}
function cleanTable() {
  document.querySelector("#invoiceList").innerHTML = "";
}
/////////////////////////////////
////////// CREATE form //////////
function showCreateForm() {
  hideEditForm();
  if (document.querySelector("#creationForm").style.display == "none") {
    unHideCreationForm();
  } else {
    hideCreationForm();
  }
}
function createInvoice() {
  hideCreationForm();
  hideEditForm();
  unHideCreationForm();
  // Request Definition.
  var request = server_url + "/api/invoice/add?";
  var finalRequest;
  // Get invoice atributes.
  var preTax = document.querySelector("input[name=preTax]").value;
  if (preTax > 0) {
  } else {
    preTax = 0;
  }
  var clientId = document.querySelector("select[name=clientId]").value;
  var statusId = document.querySelector("select[name=statusId]").value;
  var paymentStatusId = document.querySelector("select[name=paymentStatusId]").value;
  var sectorId = document.querySelector("select[name=sectorId]").value;

  var creationRequest =
    request +
    "preTax=" +
    preTax +
    "&clientId=" +
    clientId +
    "&sectorId=" +
    sectorId +
    "&statusId=" +
    statusId +
    "&paymentStatusId=" +
    paymentStatusId;
  finalRequest = creationRequest;

  var auxUrl = search_url;
  auxUrl = auxUrl.split("?");

  if (auxUrl[1].length > 0) {
    finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
  } else {
    finalRequest = finalRequest + "&page=" + currentPageGlobal;
  }

  fetchRequest(finalRequest, "#createToast");

  clearCreationForm();
  hideCreationForm();
}
///////////////////////////////
////////// EDIT form //////////
function showEditForm(event) {
  hideCreationForm();
  hideEditForm();
  unHideEditForm();
  // Find selected Invoice ID
  var selectedInvoiceRow = event.currentTarget.closest("tr");
  selectedInvoiceId = selectedInvoiceRow.querySelector(".idIndicator").innerHTML;
  selectedId = selectedInvoiceId;

  // Fetch request.
  fetch("/api/invoice/find?id=" + selectedInvoiceId)
    .then((r) => r.json())
    .then((invoiceToEdit) => {
      console.log("Selected invoice: ", invoiceToEdit);
      // Get invoice old atributes.
      document.querySelector("input[name=preTaxEdited]").value = invoiceToEdit.preTax;
      document.querySelector("select[name=clientIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.client.id - 1
      ].selected = "selected";
      console.log(document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option"));
      document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.status.id - 1
      ].selected = "selected";
      document.querySelector("select[name=paymentStatusIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.payment.id - 1
      ].selected = "selected";
      document.querySelector("select[name=sectorIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.sector.id - 1
      ].selected = "selected";
    });
}
function editInvoice() {
  var selectedInvoiceRow = selectedId;
  // Request Definition.
  var request = server_url + "/api/invoice/add?id=" + selectedInvoiceRow + "&";
  var finalRequest;

  // Get invoice new atributes.
  var preTax = document.querySelector("input[name=preTaxEdited]").value;
  if (preTax > 0) {
  } else {
    preTax = 0;
  }
  var clientId = document.querySelector("select[name=clientIdEdited]").value;
  var statusId = document.querySelector("select[name=statusIdEdited]").value;
  var paymentStatusId = document.querySelector("select[name=paymentStatusIdEdited]").value;
  var sectorId = document.querySelector("select[name=sectorIdEdited]").value;

  var editRequest =
    request +
    "preTax=" +
    preTax +
    "&clientId=" +
    clientId +
    "&sectorId=" +
    sectorId +
    "&statusId=" +
    statusId +
    "&paymentStatusId=" +
    paymentStatusId;
  finalRequest = editRequest;

  var auxUrl = search_url;
  auxUrl = auxUrl.split("?");
  if (auxUrl[1].length > 0) {
    finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
  } else {
    finalRequest = finalRequest + "&page=" + currentPageGlobal;
  }
  // Fetch request.
  console.log(finalRequest);
  fetchRequest(finalRequest, "#editToast", false);

  clearEditForm();
  hideEditForm();
}
////////////////////////////////
////////// Method GET //////////
function getInvoices(action, sortTerm, sortMethod, resetPage) {
  if (resetPage) {
    currentPageGlobal = 1;
    let currentPage = currentPageGlobal;
    updatePages(currentPage);
  }
  // Request Definition.
  var request = server_url + "/api/invoice/get";
  var finalRequest;
  // Action selector
  switch (action) {
    case "sort": // Sort request
      // Sort Definition.
      sortRequest = request + "?action=sort&";
      // Request Parameters.
      finalRequest = sortRequest + "option=" + sortMethod + "&" + "term=" + sortTerm;
      break;

    case "searchClient": // Search by request client
      // Search Definition.
      var searchRequest = request + "?action=search&";
      // Request Parameter.
      var searchTerm = document.querySelector("#searchTerm").value;
      finalRequest = searchRequest + "option=client&term=" + searchTerm;
      break;

    default:
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  fetchRequest(finalRequest, null, true);
}
///////////////////////////////////
////////// Method DELETE //////////
function markForDelete(event) {
  console.log("Selecting invoice");
  var selectedInvoiceRow = event.currentTarget.closest("tr");
  selectedInvoiceId = selectedInvoiceRow.querySelector(".idIndicator").innerHTML;
  console.log("Selected order: " + selectedInvoiceId);
}
function deleteInvoice() {
  if (selectedInvoiceId == null) {
    console.log("Error");
  } else {
    // Request Definition.
    var request = server_url + "/api/invoice/delete?";
    var finalRequest = "";
    // Find selected Invoice ID
    var deleteRequest = request + "id=" + selectedInvoiceId;
    finalRequest = deleteRequest;
    var auxUrl = search_url;
    auxUrl = auxUrl.split("?");

    if (auxUrl[1].length > 0) {
      finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
    } else {
      finalRequest = finalRequest + "&page=" + currentPageGlobal;
    }
    console.log(finalRequest);
    fetchRequest(finalRequest, "#deleteToast", false);
    selectedInvoiceId = null;
  }
}
/////////////////////////////////////////
////////// Method Batch DELETE //////////
function batchDelete() {
  if (selectedInvoicesIds.length > 0) {
    // Request Definition.
    var request = server_url + "/api/invoice/delete?";
    var finalRequest = "";

    // Find selected Invoice ID
    var deleteRequest = request + "id=" + selectedInvoicesIds.join(",");
    finalRequest = deleteRequest;
    var auxUrl = search_url;
    auxUrl = auxUrl.split("?");

    if (auxUrl[1].length > 0) {
      finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
    } else {
      finalRequest = finalRequest + "&page=" + currentPageGlobal;
    }
    console.log("Batch delete " + finalRequest);
    fetchRequest(finalRequest, "#deleteToast", false);
    selectedInvoicesIds = [];
    document.querySelector("#batchDeleteButton").disabled = true;
    document.querySelector("#batchDeleteButton").className = "btn btn-del btn-lg btn-block filterText ";
  }
}

///////////////////////////////////////
////////// Method Fill Table //////////
function fillTable(invoices) {
  if (invoices.length == 0) {
    currentPageGlobal = currentPageGlobal - 1;
    document.querySelector("#nextPage").disabled = true;
    var afterTableContainer = document.querySelector("#afterTableContainer");
    afterTableContainer.innerHTML = "";
    var afterTableContainer = document.querySelector("#afterTableContainer");
    var noMoreResultAlert = document.createElement("div");
    noMoreResultAlert.className = "alert alert-dark";
    noMoreResultAlert.role = "alert alert";
    noMoreResultAlert.innerHTML = "No more results to show";
    afterTableContainer.appendChild(noMoreResultAlert);
  } else {
    var afterTableContainer = document.querySelector("#afterTableContainer");
    afterTableContainer.innerHTML = "";
    document.querySelector("#nextPage").disabled = false;
  }
  for (i in invoices) {
    // Row
    var row = document.createElement("tr");

    // Selection Column
    var selectionCol = document.createElement("td");
    var checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.addEventListener("change", function (event) {
      addToSelected(event);
    });
    selectionCol.appendChild(checkbox);
    row.appendChild(selectionCol);

    //  ID Column
    var idCol = document.createElement("td");
    idCol.className = "filterTextDark  idIndicator";
    idCol.innerHTML = invoices[i].id;
    row.appendChild(idCol);

    //  Creation Date Column
    var creationDateCol = document.createElement("td");
    creationDateCol.className = "filterTextDark";
    creationDateCol.innerHTML = invoices[i].creationDate;
    row.appendChild(creationDateCol);

    //  Due Date Column
    var dueDateCol = document.createElement("td");
    dueDateCol.className = "filterTextDark";
    dueDateCol.innerHTML = invoices[i].dueDate;
    row.appendChild(dueDateCol);

    //  Sector Column
    var sectorCol = document.createElement("td");
    sectorCol.className = "filterTextDark";
    sectorCol.innerHTML = invoices[i].sector.name;
    row.appendChild(sectorCol);

    //  Client Column
    var clientCol = document.createElement("td");
    clientCol.className = "filterTextDark";
    clientCol.innerHTML = invoices[i].client.firstName + " " + invoices[i].client.lastName;
    row.appendChild(clientCol);

    // Pre Tax Amount Column
    var preTaxCol = document.createElement("td");
    preTaxCol.className = "filterTextDark";
    preTaxCol.style.textAlign="right"
    preTaxCol.innerHTML = invoices[i].preTax + '???';
    row.appendChild(preTaxCol);

    // After Tax Amount Column
    var afterTaxCol = document.createElement("td");
    afterTaxCol.className = "filterTextDark";
    afterTaxCol.style.textAlign="right"
    afterTaxCol.innerHTML = invoices[i].afterTax + '???';
    row.appendChild(afterTaxCol);

    //  Status Column
    var paymentStatusCol = document.createElement("td");
    paymentStatusCol.className = "filterTextDark";
    paymentStatusCol.innerHTML = invoices[i].payment.name;
    row.appendChild(paymentStatusCol);

    //  Payment Status Column
    var statusCol = document.createElement("td");
    statusCol.className = "filterTextDark";
    statusCol.innerHTML = invoices[i].status.name;
    row.appendChild(statusCol);

    //  Actions Column
    var actionsCol = document.createElement("td");
    actionsCol.className = "filterTextDark";
    var buttogroup = document.createElement("div");
    buttogroup.className = "btn-group";
    buttogroup.role = "group";

    var editbutton = document.createElement("button");
    editbutton.type = "button";
    editbutton.className = "btn btn-secondary";
    editbutton.addEventListener("click", function (event) {
      showEditForm(event);
    });
    var editIcon = document.createElement("i");
    editIcon.className = "fas fa-edit";
    editbutton.appendChild(editIcon);
    buttogroup.appendChild(editbutton);

    var deleteButton = document.createElement("button");
    deleteButton.type = "button";
    deleteButton.className = "btn btn-secondary deleteButton";
    var deleteIcon = document.createElement("i");
    deleteIcon.className = "fas fa-trash-alt";
    deleteButton.addEventListener("click", function (event) {
      markForDelete(event);
    });
    deleteButton.setAttribute("data-target", "#deleteModal");
    deleteButton.setAttribute("data-toggle", "modal");
    deleteButton.appendChild(deleteIcon);
    buttogroup.appendChild(deleteButton);
    actionsCol.appendChild(buttogroup);
    row.appendChild(actionsCol);
    document.querySelector("#invoiceList").appendChild(row);
  }
  /* $(".deleteButton").attr("data-target", "#deleteModal");
  $(".deleteButton").attr("data-toggle", "modal"); J-QUERY DEBE ESTAR YA EN EL DOM*/
}
