<!--
 * @FileDescription: 登录页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors:wnn
 * @LastEditTime: 2021/6/14
 -->
<template>
  <q-page class="flex flex-center">
      <q-card class="q-pa-md" style="width: 40%; min-height: 400px">
        <q-form
          @submit="onSubmit"
          class="q-gutter-md"
        >
          <p align="center" style="color: royalblue;font-size: 30px;font-weight: bold " >登录</p>
          <q-input
            filled
            v-model="phone"
            label="手机号"
            lazy-rules
            :rules="[ val => val && val.length > 0 || '请填写手机号']"
          />

          <q-input
            filled
            type="password"
            v-model="password"
            label="password *"
            lazy-rules
            :rules="[
            val => val !== null && val !== '' || '请填写密码'
          ]"/>

          <div align="center">
            <q-btn label="登录" type="submit" color="primary"  />
          </div>
          <div align="center">
            <router-link to="/Register">没有账号 去注册</router-link>
          </div>
        </q-form>

      </q-card>
  </q-page>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      phone: '', // 电话号码
      password: '' // 密码
    }
  },
  methods: {
    /**
     * 提交登录表单
     */
    onSubmit () {
      const self = this
      this.$axios.post('/api/user/login', {
        phoneNumber: self.phone,
        password: self.password
      }).then((response) => {
        if (response.status === 200) {
          if (response.data.status !== 200) {
            alert(response.data.info)
            self.password = ''
          } else {
            sessionStorage.setItem('token', response.data.token)
            sessionStorage.setItem('uid', response.data.uid)
            window.location = '/#/user'
            window.location.reload()
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
</script>

<style scoped>

</style>
