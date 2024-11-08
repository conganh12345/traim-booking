$(document).ready(function() {
        $('#loginForm').on('submit', function(event) {
            event.preventDefault(); 

            const formData = $(this).serialize(); 

            $.ajax({
                url: '/auth/check-login',
                type: 'POST',
                data: formData,
                success: function(response) {
                    $('.overlay').fadeIn(500); 

					$('#successNotification').text("Đăng nhập thành công, vui lòng chờ trong giây lát.").css({
	                       backgroundColor: '#5aff4d',
	                       right: '-300px', 
	                       display: 'block'
	                   }).animate({
	                       right: '20px'
	                   }, 500);

                    setTimeout(function() {
                        $('#successNotification').animate({
                            right: '-300px' 
                        }, 500, function() {
                            $('#successNotification').hide(); 
                            $('.overlay').fadeOut(500); 
                            window.location.href = '/auth/base-layout';
                        });
                    }, 2000);
                },
                error: function(xhr, status, error) {
                    $('.overlay').fadeIn(500);
                    
                    //$('#successNotification').text('Đăng nhập thất bại: ' + xhr.responseText).css({
                    $('#successNotification').text(xhr.responseText).css({
                        backgroundColor: '#ff573e',
                        right: '-300px', 
                        display: 'block'
                    }).animate({
                        right: '20px'
                    }, 500);

                    setTimeout(function() {
                        $('#successNotification').animate({
                            right: '-300px'
                        }, 500, function() {
                            $(this).hide(); 
                            $('.overlay').fadeOut(500);
                        });
                    }, 2000); 
                }
            });
        });
    });