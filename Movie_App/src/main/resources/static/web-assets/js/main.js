console.log("Xin chào")

// tắt modal trailer
var modalTrailer = document.getElementById('modal-trailer');
modalTrailer.addEventListener('hidden.bs.modal', function () {
    var iframe = modalTrailer.querySelector('iframe');
    iframe.src = iframe.src;  // Reset src to stop the video
});


// Xử lý đánh giá
const stars = document.querySelectorAll('.stars i');
let currentRating = 0; // Giá trị đánh giá mặc định

// Xử lý sự kiện hover
stars.forEach((star, index) => {
    star.addEventListener('mouseover', () => {
        highlightStars(index);
    });

    star.addEventListener('mouseout', () => {
        resetStars();
    });

    star.addEventListener('click', () => {
        currentRating = index + 1;
        document.getElementById('rating-value').innerText = `${currentRating}/10`;
    });
});

// Highlight các ngôi sao từ đầu đến vị trí hover
function highlightStars(index) {
    stars.forEach((star, i) => {
        if (i <= index) {
            star.classList.remove('fa-regular');
            star.classList.add('fa-solid');
            star.style.color = '#FFD43B'; // Màu vàng cho sao
        } else {
            star.classList.remove('fa-solid');
            star.classList.add('fa-regular');
            star.style.color = '';
        }
    });
}

// Reset lại các ngôi sao theo giá trị đánh giá hiện tại
function resetStars() {
    stars.forEach((star, i) => {
        if (i < currentRating) {
            star.classList.remove('fa-regular');
            star.classList.add('fa-solid');
            star.style.color = '#FFD43B';
        } else {
            star.classList.remove('fa-solid');
            star.classList.add('fa-regular');
            star.style.color = '';
        }
    });
}

// Khi mở modal, khởi tạo trạng thái sao theo đánh giá hiện tại
document.getElementById('ratingModal').addEventListener('shown.bs.modal', () => {
    resetStars();
});