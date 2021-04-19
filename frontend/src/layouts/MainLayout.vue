<!--
 * @FileDescription: 布局文件，包含一个导航栏
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/4/19
 -->

<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title>
          <q-icon class="iconfont icon-drifting"></q-icon>
          Dejavu二手车交易平台
        </q-toolbar-title>
        <q-btn flat class="q-mr-xs" :label="'当前城市：' + city"/>
        <q-btn flat label="登录"/>
        <q-btn flat label="注册"/>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MainLayout',
  data () {
    return {
      city: '北京' // 当前城市名称
    }
  }
}
</script>
