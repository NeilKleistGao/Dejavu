<!--
 * @FileDescription: 管理端布局文件
 * @Author: NeilKleistGao
 * @Date: 2021/6/4
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/4
 -->
<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title>
          <q-icon class="iconfont icon-drifting"></q-icon>
          Dejavu二手车交易平台-管理端
        </q-toolbar-title>

        <q-btn flat class="q-mr-xs" label="退出" @click="logout"/>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'AdminLayout',
  methods: {
    logout () {
      this.$axios.post('/api/user/logout', {
        uid: sessionStorage.getItem('uid'),
        token: sessionStorage.getItem('token')
      }, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        if (response.status === 200) {
          sessionStorage.removeItem('token')
          sessionStorage.removeItem('uid')
          window.location.reload()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
