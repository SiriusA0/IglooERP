/* Coded with love by Igloo team. */
////////// Server URL //////////
var server_url = "http://localhost:8080";
////////// Utilities //////////
function unHideCreationForm() {
  document.querySelector("#invoiceCreationForm").style.display = "";
}
function hideCreationForm() {
  document.querySelector("#invoiceCreationForm").style.display = "none";
}
function unHideEditForm() {
  document.querySelector("#invoiceEditForm").style.display = "";
}
function hideEditForm() {
  document.querySelector("#invoiceEditForm").style.display = "none";
}
/////////////////////////////////
////////// CREATE form //////////
function showCreateForm() {
  hideEditForm();
  if (document.querySelector("#orderForm").style.display == "none") {
    unHideCreationForm();
  } else {
    hideCreationForm();
  }
}
///////////////////////////////
////////// EDIT form //////////
var selectedId;
function showEditForm(event) {
  hideCreationForm();
  hideEditForm();
  unHideEditForm();
  // Find selected Invoice ID
  var selectedInvoiceRow = event.currentTarget.closest("tr");
  selectedInvoiceId = selectedInvoiceRow.querySelector(".idIndicator").innerHTML;
  selectedId = selectedInvoiceId;

  // Fetch request.
  fetch("/api/order/find?id=" + selectedInvoiceId)
    .then((r) => r.json())
    .then((orderToEdit) => {
      console.log("Selected order: " + orderToEdit.id);
      // Get order old atributes.
      document.querySelector("input[name=totalEditAmount]").value = orderToEdit.totalAmount;
      document.querySelector("select[name=clientEditId]").value = orderToEdit.clientId;
      document.querySelector("select[name=agentEditId]").value = orderToEdit.agentId;
      document.querySelector("select[name=statusEditId]").value = orderToEdit.statusId;
      document.querySelector("select[name=sectorEditId]").value = orderToEdit.sectorId;
    });
}







////////////////////////////////
////////// Method GET //////////
function getInvoices(action, sortTerm, sortMethod) {
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
      var searchTerm = document.querySelector("#searchTermClient").value;
      finalRequest = searchRequest + "option=client&term=" + searchTerm;
      break;

    default:
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  fetch(finalRequest)
    .then((r) => r.json())
    .then((invoices) => {
      cleanTable();
      console.log("Invoices", invoices);
      fillTable(invoices);
    });
}
