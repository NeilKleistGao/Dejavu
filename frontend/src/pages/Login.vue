<!--
 * @FileDescription: 登录页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/4/19
 -->
<template>
  <q-page class="flex flex-center">
    <div class="q-pa-md">
      <div class="q-gutter-md" style="max-width:900px">
        <q-input rounded filled type="tel" label="电话号码"/>
        <q-input rounded filled  label="密码">
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd =!isPwd"
              />
          </template>
        </q-input>
        <q-btn push @click.native="login"  color="primary" label="登陆"/>
      </div>
    </div>

    <!--ADD YOUR VUE CODE HERE-->
  </q-page>
</template>

<script>
export default {
  name: 'Login',
  methods: {
    login () {
      this.$router.push('/Index')
    }
  }
}
</script>

<style scoped>

</style>
