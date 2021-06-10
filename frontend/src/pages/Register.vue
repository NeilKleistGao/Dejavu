<!--
 * @FileDescription: 登录页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors:wnn
 * @LastEditTime: 2021/6/7
 -->
<template>
  <q-page class="flex flex-center">
    <div class="q-pa-md" style="width: 800px">

      <q-form
        @submit="onSubmit"
        class="q-gutter-md"
      >
        <p align="center" style="color: royalblue;font-size: 30px;font-weight: bold " >注册</p>
        <q-input
          filled
          v-model="name"
          label="用户名称 *"
          hint="uName"
          lazy-rules
          :rules="[ val => val && val.length > 0 || 'Please type something']"
        />

        <q-input
          filled
          type="number"
          v-model="telNumber"
          label="电话号码 *"
          lazy-rules
          :rules="[
          val => val !== null && val !== '' || 'Please type your password',
          val => val > 0 && val == 11 || 'Please type a real telephone number'
        ]"
        />

        <q-input
          filled
          v-model="email"
          label="邮箱 *"
          lazy-rules
          :rules="[
          val => val !== null && val !== '' || 'Please type your password',
          val => val > 0 && val <200 || 'Please type a real email address'
        ]"
        />

        <q-input
          filled
          v-model="password"
          label="密码 *"
          lazy-rules
          :rules="[
          val => val !== null && val !== '' || 'Please type your password',
          val => val > 0 && val <100 || 'Please type a real password number'
        ]"
        />

        <q-toggle v-model="accept" label="I accept the license and terms" />

        <div align="center">
          <q-btn label="注册" type="submit" color="primary"  />
        </div>
        <div align="center">
          <router-link to="/Login">已有账号 去登陆</router-link>
          <router-view></router-view>
        </div>
      </q-form>

    </div>

    <!--ADD YOUR VUE CODE HERE-->
  </q-page>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      name: null,
      telNumber: null,
      email: null,
      password: null,
      accept: false
    }
  },
  methods: {
    register () {
      this.$router.push('/Index')
    },
    onSubmit () {
      if (this.accept !== true) {
        this.$q.notify({
          color: 'red-5',
          textColor: 'white',
          icon: 'warning',
          message: 'You need to accept the license and terms first'
        })
      } else {
        this.$q.notify({
          color: 'green-4',
          textColor: 'white',
          icon: 'cloud_done',
          message: 'Submitted'
        })
      }
    }
  }
}
</script>

<style scoped>

</style>

<style scoped>

</style>
