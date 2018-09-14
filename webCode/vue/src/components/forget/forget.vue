<template>
  <div class="forget-progress">
  <el-steps :active="active" align-center>
    <el-step title="步骤1"></el-step>
    <el-step title="步骤2"></el-step>
    <el-step title="步骤3"></el-step>
  </el-steps>
    <el-form ref="form" class="el-form" :rules="rules" :model="form" label-width="100px">
        <div class="basicInfo" v-if="active===0">
          <el-form-item prop="account">
            <el-input  placeholder="" class="inp"  v-model='form.account' auto-complete="true"></el-input>
          </el-form-item>
          <el-form-item label="" class="" prop="input5">
            <el-input class="inp check" v-model="form.input5" placeholder="验证码" auto-complete="true" ></el-input>
            <el-button class="btn-login sub"   type="primary">获取验证码</el-button>
          </el-form-item>
        </div>
        <div class="basicNext" v-if="active===1">
          <el-form-item label="登陆密码 " prop="emailPass">
            <el-input type="password" v-model='form.emailPass' auto-complete="true"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkemaiPass">
            <el-input type="password" v-model='form.checkemaiPass' auto-complete="true"></el-input>
          </el-form-item>
        </div>
        <div class="basicComp" v-if="active===3">
          <h2>恭喜您，重置密码成功！</h2>
          <router-link to="/login">去登陆</router-link>
        </div>
        <div class="deployBtn">
          <el-button style="margin-top: 12px;" @click="prev" v-if="active==1||active==2">上一步</el-button>
          <el-button style="margin-top: 12px;" @click="next" v-if="active==0||active==1">下一步</el-button>
          <el-button style="margin-top: 12px;" @click="submitForm('form')" v-if="active==2">提交</el-button>
        </div>
    </el-form>

  </div>


</template>
<script>


  import axios from 'axios'
  export default {
      data() {
        const pPattern = /^.*(?=.{4,})(?=.*\d)(?=.*[!@#$%^&*? ]).*$/;
        const validateAccount = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('不能为空!'))
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
          active: 0,
          time1: 0,
          form: {
            account: '',
            input5: '',
            checkPass: '',
            emailPass: '',
          },
          // 校验规则
          rules: {
            // 校验手机号码，主要通过validator来指定验证器名称
            account: [{validator: validateAccount, trigger: 'blur'}],
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
            ],

          },


        }
      },
     methods: {
      prev(){
        --this.active;
        if (this.active < 0) this.active = 0;
      },
      next() {
        if (this.active++ > 2) this.active = 0;
      },
       submitForm (formName) {
         this.$refs[formName].validate(valid => {
           if (valid) {
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
<style scoped>
  .sub {
    position: absolute;
    right: 0;
    width: 44%;
  }
  .check{
    width: 52%;
    float: left;
    margin-left: 2px;
  }
</style>
