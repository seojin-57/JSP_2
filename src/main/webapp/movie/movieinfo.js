/**
 * 
 *//**
 * 
 */
$(document).ready(function() {
    var rating = 0;

    // 마우스가 별 위로 올라갔을 때
    $('.star').on('mouseenter', function() {
        var value = $(this).data('value');
        highlightStars(value);
    });

    // 마우스가 별에서 벗어났을 때
    $('.star').on('mouseleave', function() {
        highlightStars(rating); // 현재 선택된 별로 돌아가기
    });

    // 별 클릭 시 선택된 별로 고정
    $('.star').on('click', function() {
        rating = $(this).data('value');
        highlightStars(rating);
    });

    // 별을 하이라이트하는 함수
    function highlightStars(value) {
        $('.star').each(function() {
            if ($(this).data('value') <= value) {
                $(this).addClass('hovered');
            } else {
                $(this).removeClass('hovered');
            }
        });
    }
});
