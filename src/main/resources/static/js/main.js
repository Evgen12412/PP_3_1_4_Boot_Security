$('#editModal').on('shown.bs.modal', function (e) {
    let button = $(e.relatedTarget);
    let userId = button.data('userid');

    if (userId) {
        $.get({
            url: '/api/v1/user/' + userId,
            success: (data) => {
                let modal = $(this);
                modal.find('#user-name').val(data.username);
                modal.find('#user-surname').val(data.surname);
                modal.find('#user-password').val(data.password);
                modal.find('#user-age').val(data.age);
                modal.find('#user-email').val(data.email);
                modal.find('#user-role').val(data.role);
            },
            error: (err) => {
                alert(err);
            }
        });
    }
})
$('save-user-btn').click(function () {
    let modal = $('editModal');
    let user = {
        username: modal.find('#user-name').val(),
        surname: modal.find('#user-surname').val(),
        password: modal.find('#user-password').val(),
        age: modal.find('#user-age').val(),
        email: modal.find('#user-email').val(),
        role: modal.find('#user-role').val(),
    };
    if (modal.find('#user-id').val()) {
        user.id = modal.find('#user-id').val()
    }
    $.ajax({
        url: '/api/v1/user/',
        type: 'POST',
        data: JSON.stringify(user),
        contentType: "json",
        success: () => {
            location.reload();
        },
        error: (err) => {
                alert(err);
            }
    })
    modal.modal('hide');
})
