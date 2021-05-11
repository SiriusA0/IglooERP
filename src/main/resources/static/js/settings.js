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

//
function showUser() {}
function showCountry() {}
function showRegion() {}
function showCity() {}
//
