console.log("Xin chào")
var modalTrailer = document.getElementById('modal-trailer');
modalTrailer.addEventListener('hidden.bs.modal', function () {
    var iframe = modalTrailer.querySelector('iframe');
    iframe.src = iframe.src;  // Reset src to stop the video
});
