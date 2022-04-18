$(document).ready(function() {
    $('#passwordConfirm').keyup(function() {
        var password = $("#password").val();
        var passwordConfirm = $("#passwordConfirm").val();

        if (password != passwordConfirm) {
            $('#errorBlock').css('display', 'block').html('Passwords do not match');
            $('#sbmBtn').prop('disabled', true);
        } else {
            $('#errorBlock').css('display', 'none').html('');
            $('#sbmBtn').prop('disabled', false);

        }
    });
});