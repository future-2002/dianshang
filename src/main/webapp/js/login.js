const container = document.querySelector('#container');
const signInButton = document.querySelector('#signIn');
const signUpButton = document.querySelector('#signUp');

signUpButton.addEventListener('click', () => container.classList.add
    ('right-panel-active'));
signInButton.addEventListener('click', () => container.classList.remove
    ('right-panel-active'));

let login = document.querySelector('#login')
let register = document.querySelector('#register')

//注册的输入框
let registerUsername = document.querySelector('#registerUsername')
let registerPassword = document.querySelector('#registerPassword')
let registerEmail = document.querySelector('#registerEmail')
let verificationCode = document.querySelector('#verificationCode')
let sendBtn = document.querySelector('#send_code')

//发送验证码
sendBtn.addEventListener('click', function () {
    //倒计时
    let countDown = 60
    let timer
    timer = setInterval(() => {
        countDown--
        if (countDown > 0) {
            this.innerHTML = `倒计时${countDown}秒后可继续发送`
            this.style.backgroundColor = 'grey'
            this.disabled = true
        } else {
            clearInterval(timer)
            this.innerHTML = '发送验证码'
            this.style.backgroundColor = '#417dff'
            this.disabled = false
        }
    }, 1000);
    let random = Math.floor(Math.random() * (9999 - 1000 + 1) + 1000)
    verificationCode.value = random
})

//弹出框函数
function eject(element, content) {
    let ele = document.querySelector(element)
    ele.innerHTML = content
    ele.style.display = 'block'
    ele.classList.add('ejectMove')
}



//正则
//4-6位数字字母下划线减号
let usernameReg = /^[\w-]{4,16}$/
//至少包括大小写,数字,特殊字符三种
let passwordReg = /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\W_!@#$%^&*`~()-+=]+$)(?![0-9\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\W_!@#$%^&*`~()-+=]/

//正则提示
let registerUsernameDiv = document.querySelector('.registerUsernameDiv')
let registerPasswordDiv = document.querySelector('.registerPasswordDiv')

registerUsername.addEventListener('focus',function(){
    if (usernameReg.test(registerUsername.value)) {
        registerUsernameDiv.style.display = 'none'
    } else {
        registerUsernameDiv.style.display = 'block'
    }
})

registerUsername.addEventListener('input', function () {
    if (usernameReg.test(registerUsername.value)) {
        registerUsernameDiv.style.display = 'none'
    } else {
        registerUsernameDiv.style.display = 'block'
    }
})

registerPassword.addEventListener('focus', function () {
    if (passwordReg.test(registerPassword.value)) {
        registerPasswordDiv.style.display = 'none'
    } else {
        registerPasswordDiv.style.display = 'block'
    }
})

registerPassword.addEventListener('input', function () {
    if (passwordReg.test(registerPassword.value)) {
        registerPasswordDiv.style.display = 'none'
    } else {
        registerPasswordDiv.style.display = 'block'
    }
})



//注册按钮
register.addEventListener('click', function () {
    if (usernameReg.test(registerUsername.value) && passwordReg.test(registerPassword.value)) {
        let users = JSON.parse(localStorage.getItem('users')) || []
        let result = users.find(item => item.username == registerUsername.value)
        if (result) {
            eject('.eject', '用户名已经存在')

        } else {
            //密码加密
            let password = hex_md5(registerPassword.value)
            let userObj = {
                username: registerUsername.value,
                password,
                email: registerEmail.value,
            }
            users = [userObj, ...users]
            localStorage.setItem('users', JSON.stringify(users))
            let loginEject = document.querySelector('.eject')
            loginEject.style.display = 'block'
            loginEject.classList.add('ejectMove')
            setTimeout(() => {
                container.classList.remove('right-panel-active')
            }, 1500);
            registerUsername.value = ''
            registerPassword.value = ''
            registerEmail.value = ''
            verificationCode.value = ''
        }
    } else {
        eject('.eject', '账号或密码不合规')
    }
})

//登陆的输入框
let loginUsername = document.querySelector('#loginUsername')
let loginPassword = document.querySelector('#loginPassword')

//登录按钮
login.addEventListener('click', function () {
    if (loginUsername.value == '' || loginPassword.value == '') {
        alert('账号密码请输入正确格式')
    } else {
        //加密数据
        let loginPasswordValue = hex_md5(loginPassword.value)
        console.log(loginPasswordValue);
        let loginUsernameValue = loginUsername.value
        let users = JSON.parse(localStorage.getItem('users')) || []

        let result = users.find(item => item.username === loginUsernameValue && item.password === loginPasswordValue)
        console.log(result);
        if (result) {
            eject('.ejectLog', '登陆成功')
            loginUsername.value = ''
            loginPassword.value = ''
            setTimeout(() => {
                location.href = './dist/index.html'
            }, 1500);
        } else {
            eject('.ejectLog', '账号或密码错误')
        }
    }
})

