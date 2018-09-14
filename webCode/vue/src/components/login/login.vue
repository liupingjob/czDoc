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
            <h2>登陆</h2>
            <el-form ref="form" class="el-form" :rules="rules" :model="form" label-width="100px">
              <el-form-item prop="phone" class="el-icon-user">
                <el-input  placeholder="请输入用户名/邮箱号/手机号" class="inp "  v-model='form.phone' auto-complete="true"></el-input>
              </el-form-item>
              <el-form-item prop="pass" class="el-icon-key">
                <el-input  placeholder="请输入密码" type="password" v-model="form.pass" auto-complete="true"></el-input>
              </el-form-item>
              <el-form-item  prop="confim" class="el-icon-lock">
                <el-input  class="check" :disabled='items.state!=true' v-model="form.confim" placeholder="验证码" auto-complete="true"></el-input>
              </el-form-item>
              <div class="con"><input type="checkbox"/><span>自动登陆</span><router-link to="/forget">忘记密码</router-link></div>
              <el-button class="btn-login check order" type="primary" size="medium" @click="submitThisForm('form')">立即登陆</el-button>
              <h6>还没有账号？<router-link to="/register">立即注册</router-link></h6>
            </el-form>
        <p></p>
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
      const validatePhone1 = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空!'))
        }
        else {
             const url = `http://localhost/buyerUsernameExise?username=${value}`
             axios.get(url).then(response => {
               console.log(response.data)
               if(response.data.msg=='accountExist'){
                 callback()
               }
               callback(new Error('用户名不存在!'))
             })
          }

      }
      const validatePasso = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
            callback()
        }
      }


      const validatePasss=(rule,value,callback)=>{
        if (value === '') {
          callback(new Error('请输入验证码'))
        } else {
           const url = `http://localhost/validatePicVCode?vcode=${value}`
           axios.get(url).then(response => {
             console.log(response.data)
             if (response.data.msg == 'vcodeCorrect') {
               callback()
             }
             callback(new Error('验证码输入有误'))
           })
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

        form: {
          pass: '',
          phone:'',
          confim:'',

        },
        // 校验规则
        rules: {
          // 校验手机号码，主要通过validator来指定验证器名称
          phone: [{ validator: validatePhone1, trigger: 'blur' }],
          confim: [{ validator: validatePasso, trigger: 'blur' }],
          pass: [{ validator: validatePasss, trigger: 'blur' }],
        }
      }
    },
    methods: {
      submitThisForm (formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {

            axios.get(`http://localhost/buyerLogin?username=${this.form.phone}&&pwd=${this.form.pwd}`)
            then(response => {
              console.log(response.data)
              if(response.data.msg=='success'){
                callback()
              }
              else if(response.data.msg=='passwordError'){
                callback(new Error('密码错误!'))
              }else{
                this.items.state=true
                callback(new Error('请输入验证码!'))
              }

            })
              .catch(function (error) {
                console.log(error);
              });
          }
          else {
            console.log('error submit!!')
            return false
          }
        })
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
  h1, h2{
    font-weight: normal;
    display: inline-block;
    color:#039BC3;
  }
 h6{
    font-weight:normal;
    display: block;
    text-align: center;
    margin-left: 28%;
   font-size: 12px;
  }
  .header{
    margin-top: 34px;
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
    margin-left: 22%;
    margin-top:-1px;
    font-size: 12px;
  }
  .con span{
    display: inline-block;
    margin-right: 54%;
  }
  .con a{
    text-decoration: none;
  }
  .el-icon-user:before,.el-icon-key:before,.el-icon-lock:before{
    position: absolute;
    left: 62%;
    font-size: 28px;
  }
  .el-input {
    width: 122%;
  }
  .el-icon-user:before{
    top: 40%;
  }
  .el-form-item__content{
    margin-left: 36px!important;
  }
  .el-icon-key:before{
    top: 51%;
  }
  .el-icon-lock:before{
     top: 60%;
    left: 62.5%;
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
    margin-top: 18px;
    margin-bottom: 15px;
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
  a {
    color: #40AAFF;
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
    /*background-image: url('login.png');*/
    background: url('./login.png');
    background-size:cover;
    height: 440px;
    padding-top: 8%;
    padding-left: 58%;
  }
  .middle .hello{
    width: 377px;
    height: 388px;
    vertical-align: middle;
    background-color: #FFFFFF;
    border-radius: 8px;
    padding: 8px;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }
  .check{
    width: 69%!important;
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
