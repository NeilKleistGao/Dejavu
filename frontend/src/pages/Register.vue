<!--
 * @FileDescription: 登录页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors:wnn
 * @LastEditTime: 2021/6/14
 -->
<template>
  <q-page class="flex flex-center">
    <q-card class="q-pa-md" style="width: 40%">

      <q-form
        @submit="onSubmit"
        class="q-gutter-md"
      >
        <p align="center" style="color: royalblue;font-size: 30px;font-weight: bold " >注册</p>
        <q-input
          filled
          v-model="name"
          label="用户名称 *"
          lazy-rules
          :rules="[ val => val && val.length > 0 || '请输入名称']"
        />

        <q-input
          filled
          v-model="telNumber"
          label="电话号码 *"
          lazy-rules
          :rules="[
          val => val.length > 0 && val.length === 11 || '请输入正确的电话号码'
        ]"
        />

        <q-input
          filled
          v-model="email"
          label="邮箱 *"
          lazy-rules
          :rules="[
          val => val.length > 0 && val.length <200 || '请输入正确的邮箱地址'
        ]"
        />

        <q-input
          filled
          type="password"
          v-model="password"
          label="密码 *"
          lazy-rules
          :rules="[
          val => val.length > 0 && val.length <100 || '请输入密码'
        ]"
        />

        <q-toggle v-model="accept" label="我已阅读并接受条款" />

        <div align="center">
          <q-btn label="注册" type="submit" color="primary"  />
        </div>
        <div align="center">
          <router-link to="/Login">已有账号 去登陆</router-link>
        </div>
      </q-form>

      <q-dialog v-model="alert">
        <q-card style="min-width: 400px">
          <q-card-section>
            <div class="text-h5">提示</div>
          </q-card-section>
          <q-card-section>
            {{hint}}
          </q-card-section>
          <q-card-actions align="right">
            <q-btn flat label="确定" @click="alert = false"/>
          </q-card-actions>
        </q-card>
      </q-dialog>
    </q-card>
  </q-page>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      name: '', // 用户昵称
      telNumber: '', // 电话号码
      email: '', // 电子邮箱
      password: '', // 密码
      accept: false, // 是否接受用户协议（然而并没有用户协议）
      alert: false, // 是否显示提示信息
      hint: '' // 提示信息内容
    }
  },
  methods: {
    /**
     * 提交注册表单
     */
    onSubmit () {
      if (this.accept !== true) {
        this.hint = '请阅读并接受条款'
        this.alert = true
      } else {
        const self = this
        this.$axios.post('/api/user/register', {
          phoneNumber: this.telNumber,
          mail: this.email,
          password: this.password,
          name: this.name
        }).then(function (response) {
          console.log(response)
          if (response.status === 200) {
            if (response.data.result === -1) {
              self.hint = '用户已存在'
              self.alert = true
            } else {
              self.$router.push('/Login')
            }
          }
        })
      }
    },
    beforeRouteEnter (to, from, next) {
      if (sessionStorage.getItem('token') !== null) {
        window.location = '/#/user'
      } else {
        next()
      }
    }
  }
}
</script>

<style scoped>

</style>

<style scoped>

</style>
