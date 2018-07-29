function login() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var roleName = $("#roleName").val();
    if (userName == null || userName == "") {
        swal({
            title: '用户名不能为空！',
            type: 'error',
        });
        return;
    }
    if (password == null || password == "") {
        swal({
            title: '密码不能为空！',
            type: 'error',
        });
        return;
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/admin/login",
        data: $('#adminlogin').serialize(),
        success: function (result) {
            if (result.resultCode == 200) {
                setCookie("userName", result.data.currentUser.name);
                setCookie("roleName", result.data.currentUser.pwd);
                window.location.href = "main.jsp";
            }else {
                swal({
                    title: '用户名或密码错误',
                    type: 'error',
                })
            }
        },
        error: function () {
            alert("登录异常！");
        }
    });

}