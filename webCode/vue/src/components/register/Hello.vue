<template>
  <div class="wrapper">
    <div class="header">
      <div class="top">
        <img src="./logo.png" alt="">
        <span>创展世贸电商生态平台</span>
      </div>
    </div>
    <div class="middle">
      <div class="hello" >
      <el-tabs v-model="activeName"  @tab-click="handleClick">
        <el-tab-pane label="手机注册"  name="first">
          <el-form ref="form" class="el-form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="手 机 号" prop="phone" >
              <el-input class="inp"  v-model='form.phone' auto-complete="true"></el-input>
            </el-form-item>
            <el-form-item label="" class="" prop="input1">
              <el-input class="inp check" v-model="form.input1" placeholder="验证码" auto-complete="true"></el-input>
              <el-button class="btn-login sub" :disabled="disabled" @click="sendMsg" type="primary">{{btntxt}}</el-button>
            </el-form-item>
            <el-form-item label="登陆密码 " prop="pass">
              <el-input type="password" v-model="form.pass" auto-complete="true"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="form.checkPass" auto-complete="true"></el-input>
            </el-form-item>
            <div class="con"><input type="checkbox" :checked='items.state==true' v-on:click="alocked(items)"/><el-button type="text" @click="open2">我已阅读并同意《创展世贸xx协议》</el-button></div>
            <el-button class="btn-login check order" type="primary" :disabled='items.state!=true' size="medium" @click="submit('form')">立即注册</el-button>
            <h5><span>已经注册可以在此点击</span><router-link to="/login">登陆</router-link></h5>
          </el-form>
            </el-tab-pane>
        <el-tab-pane label="邮箱注册" name="second">
          <el-form ref="form" :rules="rules" class="el-form" :model="form" label-width="100px">
            <el-form-item label="邮  箱 " prop="email" >
              <el-input class="inp"  v-model='form.email' auto-complete="true"></el-input>
            </el-form-item>
            <el-form-item label="" class="" prop="input5">
              <el-input class="inp check" v-model="form.input5" placeholder="验证码" auto-complete="true" ></el-input>
              <el-button class="btn-login sub" :disabled="isdisabled" @click="sendCode" type="primary">{{buttonName}}</el-button>
            </el-form-item>
            <el-form-item label="登陆密码 " prop="emailPass">
              <el-input type="password" v-model='form.emailPass' auto-complete="true"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkemaiPass">
              <el-input type="password" v-model='form.checkemaiPass' auto-complete="true"></el-input>
            </el-form-item>
            <div class="con"><input type="checkbox"  :checked='items.state==true' v-on:click="alocked(items)"/><el-button type="text" @click="open2">我已阅读并同意《创展世贸xx协议》</el-button></div>
            <el-button class="btn-login check order" type="primary" :disabled='items.state!=true' size="medium" @click="submitForm('form')">立即注册</el-button>
            <h5><span>已经注册可以在此点击</span><router-link to="/login">登陆</router-link></h5>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
    </div>
    <div class="footer">
      <p><a href="">首页</a>|<a href="">隐私保护</a>|<a href="">联系我们</a>|<a href="">免费条款</a>|<a href="">商家入驻</a>|<a href="">意见反馈</a></p>
      <p class="message">©2009-2018深圳市创展世贸国际展览有限公司 粤ICP备16056256号-1</p>
    </div>
  </div>
</template>



<script>

  import axios from 'axios'
  axios.defaults.withCredentials=true;
  export default {
    data () {

      const phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/
      const emailReg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
      const validatePhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('号码不能为空!'))
      }
      setTimeout(() => {
        if (!phoneReg.test(value)) {
          callback(new Error('请输入有效的手机号!'))
        } else {
          this.form.phone=value
          const url = `http://localhost/buyerAccountTelExise?uTel=${this.form.phone}`
          axios.get(url).then(response => {
            if(response.data.msg=='canRegister'){

              callback()
            }
            console.log(response.data)
            callback(new Error('手机号已经注册!'))
          })
        }
      }, 10)
    }
      //密码正则，最少6位，包括至少1个数字，1个特殊字符
      const pPattern = /^.*(?=.{4,})(?=.*\d)(?=.*[!@#$%^&*? ]).*$/;
      console.log("=="+pPattern.test("iFat3#"));
      const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (!pPattern.test(value)) {
          callback(new Error('最少6位，包括1个数字，1个特殊字符'))
        }
        this.form.pass=value
        callback()
      }
    }
      const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        this.form.email=value
        callback()
      }
    }
      const validateEmail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('邮箱不能为空!'))
        }
        setTimeout(() => {
          if (!emailReg.test(value)) {
            callback(new Error('请输入有效的邮箱地址!'))
          } else {
            this.value=value
            const url = `http://localhost/buyerAccountEmailExise?email=${value}`
            axios.get(url).then(response => {
              this.form.email=value
              if (response.data.msg == 'canRegister') {
                callback()
              }
              callback(new Error('邮箱已经注册!'))
            })
          }
        }, 10)
        console.log(value)
      }
      const validatePass0=(rule,value,callback)=>{
        if (!value) {
          callback(new Error('请输入验证码'))
        } else {
          const url = `http://localhost/validateSmsVCode?vcode=${value}`
          axios.get(url).then(response => {
            console.log(response.data)
            if (response.data.msg == 'vcodeCorrect') {
              callback()
            }
            callback(new Error('验证码输入有误'))
          })

        }
      }
      const validatePass1=(rule,value,callback)=>{
        if (this.value === '') {
          callback(new Error('请输入验证码'))
        } else {
          const url = `http://localhost/validateMailVCode?vcode=${value}`
          axios.get(url).then(response => {
            console.log(response.data)
            if (response.data.msg == 'vcodeCorrect') {
              callback()
            }
            callback(new Error('验证码输入有误'))
          })

        }
      }
      const validatePass3 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (!pPattern.test(value)) {
            callback(new Error('最少4位，包括1个数字，1个特殊字符'))
          }
          this.form.emailPass=value
          callback()
        }
      }


      const validatePass4 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.form.emailPass) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }

    return {
      items: {
        state: false
      },
      isShow:false,
      isdisabled:false,
      disabled:false,
      btntxt:"获取验证码",
      activeName: 'first',
      buttonName: "获取验证码",
      time: 0,
      time1:0,
      value:'',
      phoneNum:'',
      form: {
        pass: '',
        phone:'',
        checkPass: '',
        email: '',
        emailPass: '',
        checkemaiPass: '',
      },
      // 校验规则
      rules: {
        // 校验手机号码，主要通过validator来指定验证器名称
        phone: [{ validator: validatePhone, trigger: 'blur' }],
        email: [{ validator: validateEmail, trigger: 'blur' }],
        pass: [
          {
            validator: validatePass, trigger: 'blur'
          },
          { min: 4, max: 7, message: '长度在 4 到 7 个字符', trigger: 'blur' }
        ],
        checkPass: [
          {
            validator: validatePass2, trigger: 'blur'
          }
        ],
        input5:[
          {
            validator: validatePass1, trigger: 'blur'
          }
        ],
        input1:[
          {
            validator: validatePass0, trigger: 'blur'
          }
        ],
        emailPass: [
          {
            validator: validatePass3, trigger: 'blur'
          },
          { min: 4, max: 7, message: '长度在 4 到 7 个字符', trigger: 'blur' }
        ],
        checkemaiPass: [
          {
            validator: validatePass4, trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    open2() {
      const h = this.$createElement;
      this.$msgbox({
        title: '注册协议',
        customClass:'',
        message: h('div', { style: 'height: 400px;border:1px solid black;overflow:auto;padding:4px;text-indent:32px'}, [
          h('div', { }, [
          h('h3', { style: 'color: teal' }, '版本生效日期：2018年08月21日'),
          h('p', null, '欢迎您与各创展世贸平台经营者（详见定义条款）共同签署本《创展世贸平台服务协议》（下称“本协议”）并使用创展世贸平台服务！'),
          h('p', null, '欢迎您与各创展世贸平台经营者（详见定义条款）共同签署本《创展世贸平台服务协议》（下称“本协议”）并使用创展世贸平台服务！本协议为《淘宝服务协议》修订版本，自本协议发布之日起，创展世贸平台各处所称“创展世贸服务协议”均指本协议。'),
          h('p', null, '欢迎您与各创展世贸平台经营者（详见定义条款）共同签署本《创展世贸平台服务协议》（下称“本协议”）并使用创展世贸平台服务！各服务条款前所列索引关键词仅为帮助您理解该条款表达的主旨之用，不影响或限制本协议条款的含义或解释。为维护您自身权益，建议您仔细阅读各条款具体表述。'),
          h('p', null, '【审慎阅读】您在申请注册流程中点击同意本协议之前，应当认真阅读本协议。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款、法律适用和争议解决条款。免除或者限制责任的条款将以粗体下划线标识，您应重点阅读。如您对协议有任何疑问，可向创展世贸平台客服咨询。'),
          h('p', null, '【签约动作】当您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，即表示您已充分阅读、理解并接受本协议的全部内容，并与创展世贸达成一致，成为创展世贸平台“用户”。阅读本协议的过程中，如果您不同意本协议或其中任何条款约定，您应立即停止注册程序。'),
        ])]),
        showCancelButton: true,
        confirmButtonText: '同意',
        cancelButtonText: '不同意',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
              done();
              this.items.state=true
          } else {
            done();
          }
        }
      })

    },
    alocked: function (items) {
      items.state = !items.state;
    },
    submit(formName){
      this.$refs[formName].validate(valid => {
        if (valid) {

           axios.get(`http://localhost/buyerSignup?uTel=${this.form.phone}&&pwd=${this.form.pass}`)
             .then(function (response) {
               console.log(response);
             })
             .catch(function (error) {
               console.log(error);
             });
          this.$router.push('/main')
        }
        else {
          console.log('error submit!!')
          return false
        }
      })
    },
    submitForm (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {

            axios.get(`http://localhost/buyerSignup?email=${this.form.email}&&pwd=${this.form.emailPass}`)
              .then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });
          this.$router.push('/login')
//          this.$router.push({ name: 'login', params: { userId: '' }})
          }
         else {
          console.log('error submit!!')
          return false
        }
      })
    },
    sendMsg(){
        this.time=60
        this.disabled=true
        this.timer()
      axios.get(`http://localhost/buyerSendSmsVcode?phoneNum=${this.form.phone}`,{
        xhrFields :
          {
            withCredentials: true
          }
      }).then(

        res=>{

          console.log(res.data)
        })
        /*axios.post(url).then(
         res=>{
         this.phonedata=res.data;
         })*/
    },
    sendCode(){
      this.time1=60
      this.isdisabled=true
      this.timer1()
      axios.get(`http://localhost/buyerSendMailVcode?mailUrl=${this.value}`,{
        xhrFields :
          {
            withCredentials: true
          }
      }).then(

       res=>{

       console.log(res.data)
       })
    },
    sendMsg(){
      this.time=60
      this.disabled=true
      this.timer()
      axios.get(`http://localhost/buyerSendSmsVcode?phoneNum=${this.form.phone}`,{
        xhrFields :
          {
            withCredentials: true
          }
      }).then(

        res=>{

          console.log(res.data)
        })

    },
    timer() {
      if (this.time > 0) {
        this.time--;
        this.btntxt=this.time+"s后重新获取";
        setTimeout(this.timer, 1000);
      } else{
        this.time=0;
        this.btntxt="获取验证码";
        this.disabled=false;
      }
    },
    timer1() {
      if (this.time1 > 0) {
        this.time1--;
        this.buttonName=this.time1+"s后重新获取";
        setTimeout(this.timer1, 1000);
      } else{
        this.time1=0;
        this.buttonName="获取验证码";
        this.isdisabled=false;
      }
    },
    handleClick (tab, event) {
      console.log(tab, event)
    }
  }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  html, body, div, span{
    margin: 0;
    padding: 0;
  }
  .wrapper{
    margin-top: 34px;
  }
  h1, h2{
  font-weight: normal;
  display: inline-block;
}
  .el-tabs--top{
    font-size: 16px;
  }
  h5{
    font-weight:normal;
    display: block;
    text-align: center;
    margin-left: 28%;
  }
  .header{
    padding-left: 80px;
}
  .top{
  display: table-cell;
  text-align: center;
  height: 80px;
  min-width: 510px;
}
  .sub {
    position: absolute;
    right: 0;
    width: 44%;
  }
  .con{
    margin-left: 27%;
    margin-top: -8px;
  }
  .btn-login{
    border-radius: 8px;
  }
  .first .el-form{
    width: 269px;
  }
  .el-form{
    width: 88%;
  }
  .el-form .order {
    width: 73%;
    margin-left: 28%;
    border-radius: 8px;
    margin-bottom: 10px;
  }
  .el-tab-pane{
    width: 100%;
  }
  .el-input__inner {
    border-radius: 8px;
  }
  .el-form-item {
    margin-bottom: 18px;

  }
  .middle{
    position: relative;
  }
  .el-tabs__item{
    padding: 0 15px;
    font-size: 16px;
  }
  .el-tabs__item.is-active,.el-tabs__item:hover{
    color: #1CA2C7;
  }
  .el-tabs__nav-wrap::after,.el-tabs__active-bar{
  height: 0;
  }
  .el-tabs__nav-scroll{
    text-align: center;
  }
  .el-tabs__nav {
    margin-left: 37%;
  }

  .top img{
  height: 68px;
  vertical-align: middle;
  text-align: center;
}
  .top span{
  display: inline-block;
  height: 12px;
  font-size: 22px;
  margin-left: 30px;
  color: #4EB0CF;
  font-weight: 500;
  vertical-align: middle;
}
.middle{
  background-image: url('./register.png');
  background-size:cover;
  height: 440px;
  padding-top: 8%;
  padding-left: 58%;
}
  .middle .hello{
    width: 384px;
    height: 388px;
    vertical-align: middle;
    background-color: #FFFFFF;
    border-radius: 8px;
    padding: 8px;

    box-shadow: 0 8px 10px 0 #B5B5B5;
  }
ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #40AAFF;
}
.check{
  width: 52%;
  float: left;
  margin-left: 2px;
}
  .footer p a{
    display: inline-block;
    font-size: 14px;
    text-decoration: none;
    color:black;
    margin-right: 8px;
    margin-left: 8px;
  }
  .footer .message{
    font-size: 12px;
  }
</style>
