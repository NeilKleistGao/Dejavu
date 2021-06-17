<!--
 * @FileDescription: 管理员登录页面
 * @Author: NeilKleistGao
 * @Date: 2021/6/4
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/15
 -->
<template>
  <q-page class="flex">
    <div class="row" style="width: 100%; margin-top: 5%">
      <div class="col-4"/>
      <div class="col-4">
        <q-card class="text-center">
          <q-card-section>
            <span class="text-h4">登录</span>
          </q-card-section>
          <q-separator/>
          <q-card-section style="margin-left: 10%; width: 80%; margin-right: auto">
            <q-input v-model="username" label="用户名"/>
            <br/>
            <q-input v-model="password" label="密码" type="password"/>
          </q-card-section>

          <q-card-actions class="flex-center">
            <q-btn color="primary" size="lg" label="登录" style="padding-left: 1em; padding-right: 1em" @click="login"/>
          </q-card-actions>
        </q-card>
      </div>
      <div class="col-4"/>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'AdminLogin',
  data () {
    return {
      username: '', // 用户名
      password: '' // 密码
    }
  },
  methods: {
    /**
     * 登录
     */
    login () {
      const self = this
      this.$axios.post('/api/user/login', {
        phoneNumber: self.username,
        password: self.password
      }).then((response) => {
        if (response.status === 200) {
          if (response.data.status !== 200) {
            alert(response.data.info)
            self.password = ''
          } else {
            sessionStorage.setItem('token', response.data.token)
            sessionStorage.setItem('uid', response.data.uid)
            window.location = '/#/admin/dashboard'
            window.location.reload()
          }
        }
      })
    }
  },
  beforeRouteEnter (from, to, next) {
    if (sessionStorage.getItem('token') !== null) {
      window.location = '/#/admin/dashboard'
    } else {
      next()
    }
  }
}
</script>

<style scoped>

</style>
