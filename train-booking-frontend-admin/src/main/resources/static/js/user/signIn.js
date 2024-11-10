$(document).ready(function() {
        $('#loginForm').on('submit', function(event) {
            event.preventDefault(); 

            const formData = $(this).serialize(); 

            $.ajax({
            	// take url from form action
                url: '/auth/check-login',
                type: 'POST',
                // send form data
                data: formData,
                success: function(response) {
                	// Fade overlay for 500ms
                    $('.overlay').fadeIn(500); 

                    // Show success message with animation
                    $('#successNotification').text("Login success!, wait a second").css({
                        backgroundColor: '#5aff4d',
                        right: '-300px', 
                        display: 'block'
                    }).animate({
                    	 // Slide into place from the right
                        right: '20px'
                    }, 500);

                    // After 2 seconds, slide out and hide the overlay, then redirect the page
                    setTimeout(function() {
                        $('#successNotification').animate({
                        	// Slide off screen
                            right: '-300px' 
                        }, 500, function() {
                            $('#successNotification').hide(); 
                            $('.overlay').fadeOut(500); 
                            window.location.href = '/auth/base-layout';
                        });
                    }, 2000);
                },
                error: function(xhr, status, error) {
                    // Show dark overlay
                    $('.overlay').fadeIn(500);

                    
                    $('#successNotification').text('Login failed: ' + xhr.responseText).css({
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