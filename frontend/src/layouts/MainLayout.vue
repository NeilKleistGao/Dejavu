<!--
 * @FileDescription: 布局文件，包含一个导航栏
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/15
 -->

<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title @click="go('/#/')">
          <q-icon class="iconfont icon-drifting"></q-icon>
          Dejavu二手车交易平台
        </q-toolbar-title>

        <q-btn flat class="q-mr-xs" label="我要买车" @click="go('/#/search')"/>
        <q-btn flat class="q-mr-xs" label="我要卖车" @click="go('/#/sell')"/>
        <q-btn flat class="q-mr-xs" :label="'当前城市：' + city">
          <q-popup-proxy>
            <q-card style="width: 600px; min-height: 400px">
              <q-card-section>
                <span class="text-h4">选择城市</span>
              </q-card-section>
              <q-card-section>
                <div v-for="pro in provinces" :key="pro">
                  <div class="text-h5" style="margin-bottom: 0.2em; margin-top: 0.2em">{{pro}}</div>
                  <q-btn flat color="primary" v-for="ct in cities[pro]" :key="ct" :label="ct" @click="city=ct"/>
                </div>
              </q-card-section>
            </q-card>
          </q-popup-proxy>
        </q-btn>
        <q-separator vertical inset=""/>
        <div v-if="!hasLogin">
          <q-btn flat class="q-mr-xs" label="登录" @click="go('/#/login')"/>
          <q-btn flat class="q-mr-xs" label="注册" @click="go('/#/register')"/>
        </div>
        <div v-else>
          <q-avatar size="32px" @click="go('/#/user')">
            <img :src="avatar"/>
          </q-avatar>
          <q-btn flat class="q-mr-xs" label="退出" @click="logout"/>
        </div>

      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
    <div>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title @click="go('/#/')">
          <q-icon class="iconfont icon-drifting"></q-icon>
          <span class="text-h6">Dejavu二手车交易平台</span>
          <br/>
          <span class="text-subtitle2">Copyright 2021 www.dejavu.com All Rights Reserved</span>
        </q-toolbar-title>
      </q-toolbar>
    </div>

  </q-layout>
</template>

<script>
import CityList from '../../public/city_information/city_list.json'
export default {
  name: 'MainLayout',
  data () {
    return {
      provinces: CityList.provinces,
      cities: CityList.cities,
      avatar: 'default-avatar.jpg',
      city: '' // 当前城市名称
    }
  },
  methods: {
    /**
     * 重定向页面
     * @param url 重定向目标url
     */
    go (url) {
      window.location = url
      window.location.reload()
    },
    /**
     * 用户登出
     */
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
  },
  beforeMount () {
    let city = sessionStorage.getItem('city')
    if (city === null) {
      city = '北京市'
      sessionStorage.setItem('city', '北京市')
    }

    this.city = city
    if (sessionStorage.getItem('token') !== null) {
      const self = this
      this.$axios.get('/api/user/info/query?uid=' + sessionStorage.getItem('uid'), {
        headers: { token: sessionStorage.getItem('token') }
      }).then((response) => {
        console.log(response)
        if (response.status === 200) {
          if (response.data.avatar !== null && response.data.avatar !== undefined && response.data.avatar !== '') {
            self.avatar = response.data.avatar
          }
        }
      })
    }
  },
  watch: {
    city: {
      handler (newValue, oldValue) {
        sessionStorage.setItem('city', newValue)
        if (oldValue !== '') {
          window.location.reload()
        }
      }
    }
  },
  computed: {
    /**
     * 用户是否已经登录
     * @returns {boolean} true表示已经登录，反之返回false
     */
    hasLogin () {
      return sessionStorage.getItem('token') !== null
    }
  }
}
</script>
