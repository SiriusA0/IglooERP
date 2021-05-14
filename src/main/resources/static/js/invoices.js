/* Coded with love by Igloo team. */
$(".toast").toast();
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
  if (document.querySelector("#invoiceCreationForm").style.display == "none") {
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

  fetch(finalRequest)
    .then((r) => r.json())
    .then((invoices) => {
      cleanTable();
      fillTable(invoices);
      $(document).ready(function () {
        {
          $("#createToast").toast("show");
        }
      });
    });

  clearCreationForm();
  hideCreationForm();
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
  fetch("/api/invoice/find?id=" + selectedInvoiceId)
    .then((r) => r.json())
    .then((invoiceToEdit) => {
      console.log("Selected invoice: ", invoiceToEdit);
      // Get invoice old atributes.
      document.querySelector("input[name=preTaxEdited]").value = invoiceToEdit.preTax;
      document.querySelector("select[name=clientIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.client.id-1
      ].selected = "selected";
      console.log(document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option"));
      document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.status.id-1
      ].selected = "selected";
      document.querySelector("select[name=paymentStatusIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.payment.id-1
      ].selected = "selected";
      document.querySelector("select[name=sectorIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.sector.id-1
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

  // Fetch request.
  fetch(finalRequest)
    .then((r) => r.json())
    .then((invoices) => {
      cleanTable();
      fillTable(invoices);
      $(document).ready(function () {
        {
          $("#editToast").toast("show");
        }
      });
    });

  clearEditForm();
  hideEditForm();
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
      var searchTerm = document.querySelector("#searchTerm").value;
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
///////////////////////////////////
////////// Method DELETE //////////
function deleteInvoice(event) {
  // Request Definition.
  var request = server_url + "/api/invoice/delete?";
  var finalRequest = "";
  // Find selected Invoice ID
  var selectedInvoiceRow = event.currentTarget.closest("tr");
  selectedInvoiceId = selectedInvoiceRow.querySelector(".idIndicator").innerHTML;
  var deleteRequest = request + "id=" + selectedInvoiceId;

  finalRequest = deleteRequest;
  fetch(finalRequest)
    .then((r) => r.json())
    .then((invoices) => {
      cleanTable();
      console.log("Invoices", invoices);
      fillTable(invoices);
      $(document).ready(function () {
        {
          $("#deleteToast").toast("show");
        }
      });
    });
}
///////////////////////////////////////
////////// Method Fill Table //////////
function fillTable(invoices) {
  for (i in invoices) {
    // Row
    var row = document.createElement("tr");

    // Selection Column
    var selectionCol = document.createElement("td");
    var checkbox = document.createElement("input");
    checkbox.type = "checkbox";
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
    preTaxCol.innerHTML = invoices[i].preTax;
    row.appendChild(preTaxCol);

    // After Tax Amount Column
    var afterTaxCol = document.createElement("td");
    afterTaxCol.className = "filterTextDark";
    afterTaxCol.innerHTML = invoices[i].afterTax;
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
    var modal = document.createElement("div");

    var favbutton = document.createElement("button");
    favbutton.type = "button";
    favbutton.className = "btn btn-secondary";
    var favIcon = document.createElement("i");
    favIcon.className = "far fa-star";
    favIcon.addEventListener("click", function (event) {
      favoriteinvoice(event);
    });
    favbutton.appendChild(favIcon);
    buttogroup.appendChild(favbutton);

    var editbutton = document.createElement("button");
    editbutton.type = "button";
    editbutton.className = "btn btn-secondary";
    var editIcon = document.createElement("i");
    editIcon.className = "fas fa-edit";
    editIcon.addEventListener("click", function (event) {
      showEditForm(event);
    });
    editbutton.appendChild(editIcon);
    buttogroup.appendChild(editbutton);

    var deleteButton = document.createElement("div");
    deleteButton.innerHTML = `  
    <!-- Delete Modal trigger -->
    <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#deleteModal">
      <i class="fas fa-trash-alt"></i>
    </button>
    <!-- Modal Delete -->
    <div
      class="modal fade"
      id="deleteModal"
      data-backdrop="static"
      data-keyboard="false"
      tabindex="-1"
      aria-labelledby="deleteModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="deleteModalLabel">Confirm deletion</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">We won't be able to recover the Invoice . Are you sure?</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">
              No! Go back!
            </button>
            <button
              type="button"
              onclick="deleteInvoice(event)"
              class="btn btn-add"
              data-dismiss="modal"
            >
              Understood, delete
            </button>
          </div>
        </div>
      </div>
    </div>`;
    buttogroup.appendChild(deleteButton);
    actionsCol.appendChild(buttogroup);
    row.appendChild(actionsCol);
    document.querySelector("#invoiceList").appendChild(row);
  }
}
