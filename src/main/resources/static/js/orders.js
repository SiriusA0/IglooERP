/* Coded with love by Igloo team. */
////////// Diary of a backend, frontend hater, developer  //////////
// All orders / sort - request : /api/order/get
// Variable "action":
// if get -> bring all orders from server
// if sort -> bring orders sorted by: "amount" or "date"
// Create order - request : /api/order/add

////////// Server URL //////////
var server_url = "http://localhost:8080";

////////// Method GET //////////
// Example sort: getOrders("sort","amount","asc")
// Example search: getOrders("search","name",null)
function getOrders(action, sortTerm, sortMethod) {
  // Request Definition.
  var request = server_url + "/api/order/get";
  var finalRequest = "";
  // Action selector
  switch (action) {
    case "sort": // Sort request
      // Sort Definition.
      sortRequest = request + "?action=sort&";
      // Request Parameters.
      finalRequest = sortRequest + "sortmethod=" + sortMethod + "&" + "sortterm=" + sortTerm;
      break;

    case "search": // Search by request
      // Search Definition.
      var searchRequest = request + "?action=search&";
      // Request Parameter.
      var searchTerm = document.querySelector("#searchTerm").value;
      finalRequest = searchRequest + "searchterm=" + searchTerm;
      break;

    default:
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((orders) => {
      cleanTable();
      fillTable(orders);
    });
}

////////// Method CREATE //////////
function createOrder() {
  // Request Definition.
  var request = server_url + "/api/order/add?";
  var finalRequest = "";
  // Get order atributes.
  var totalAmount = document.querySelector("input[name=totalAmount").value;
  var clientId = document.querySelector("select[name=clientId").value;
  var agentId = document.querySelector("select[name=agentId").value;
  var statusId = document.querySelector("select[ name=statusId").value;
  var sectorId = document.querySelector("select[ name=sectorId").value;

  var creationRequest =
    request +
    "totalamount=" +
    totalAmount +
    "&clientdd=" +
    clientId +
    "&agentid=" +
    agentId +
    "&statusid=" +
    statusId +
    "&sectorid=" +
    sectorId;

  finalRequest = creationRequest;

  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((neworder) => {
      console.log("Added order: " + neworder.id);
    });
}
////////// Method DELETE //////////
function deleteOrder(event) {
  // Request Definition.
  var request = server_url + "/api/order/delete?";
  var finalRequest = "";
  // Get order atributes.
  var orderid = event.currentTarget.closest();

  var deleteRequest = request + "orderid=" + orderid;

  finalRequest = deleteRequest;

  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((neworder) => {
      console.log("Added order: " + neworder.id);
    });
}
