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
          @register="onRegister"
          class="q-gutter-md"
        >
          <p align="center" style="color: royalblue;font-size: 30px;font-weight: bold " >登录</p>
          <q-input
            filled
            v-model="name"
            label="phonenumber *"
            hint="your telephone number"
            lazy-rules
            :rules="[ val => val && val.length > 0 || 'Please type something']"
          />

          <q-input
            filled
            type="number"
            v-model="password"
            label="password *"
            lazy-rules
            :rules="[
          val => val !== null && val !== '' || 'Please type your password',
          val => val > 0 && val < 100 || 'Please type a real password'
        ]"
          />

          <q-toggle v-model="accept" label="I accept the license and terms" />

          <div align="center">
            <q-btn label="登录" type="submit" color="primary"  />
          </div>
          <div align="center">
            <router-link to="/Register">没有账号 去注册</router-link>
            <router-view></router-view>
          </div>
        </q-form>

      </div>

    <!--ADD YOUR VUE CODE HERE-->
  </q-page>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      name: null,
      password: null,
      accept: false
    }
  },
  methods: {
    login () {
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
