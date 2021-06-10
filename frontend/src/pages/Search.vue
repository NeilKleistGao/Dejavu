<!--
 * @FileDescription: 搜索页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/10
 -->
<template>
  <q-page class="flex-center">
    <div class="q-pa-md flex-center" >
      <div class="q-gutter-y-md" style="width: 60%; margin-left: 20%;">
        <div class="row" style="margin-top: 2em; margin-bottom: -2em">
          <div class="col-8">
            <q-breadcrumbs>
              <q-breadcrumbs-el label="二手车搜索"/>
              <q-breadcrumbs-el :label="city"/>
              <q-breadcrumbs-el v-if="brand !== ''" :label="brand"/>
              <q-breadcrumbs-el v-if="body !== ''" :label="body"/>
              <q-breadcrumbs-el v-if="price !== ''" :label="price + '元'"/>
              <q-breadcrumbs-el v-if="text !== ''" :label="'搜索：' + text"/>
            </q-breadcrumbs>
          </div>
          <div class="col-4">
            <q-input outlined bottom-slots dense label="搜索" v-model="text">
              <template slot="append">
                <q-icon name="search" />
              </template>
            </q-input>
          </div>
        </div>
        <q-tabs
          v-model="brand"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="" label="不限"/>
          <q-tab name="大众" label="大众" />
          <q-tab name="宝马" label="宝马" />
          <q-tab name="奔驰" label="奔驰" />
          <q-tab name="丰田" label="丰田" />
          <q-tab name="本田" label="本田" />
          <q-tab name="奥迪" label="奥迪" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="brand = '现代'">
                <q-item-section>现代</q-item-section>
              </q-item>
              <q-item clickable @click="brand = '马自达'">
                <q-item-section>马自达</q-item-section>
              </q-item>
              <q-item clickable @click="brand = '哈佛'">
                <q-item-section>哈佛</q-item-section>
              </q-item>
              <q-item clickable @click="brand='比亚迪'">
                <q-item-section>比亚迪</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
        <q-tabs
          v-model="body"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="" label="不限"/>
          <q-tab name="豪华轿车" label="豪华轿车" />
          <q-tab name="微型车" label="微型车" />
          <q-tab name="箱型车" label="箱型车" />
          <q-tab name="大巴车" label="大巴车" />
          <q-tab name="敞篷车" label="敞篷车" />
          <q-tab name="双门汽车" label="双门汽车" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="body = '商务车'">
                <q-item-section>商务车</q-item-section>
              </q-item>
              <q-item clickable @click="body = '搅拌车'">
                <q-item-section>搅拌车</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
        <q-tabs
          v-model="price"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="" label="不限"/>
          <q-tab name="0-30000" label="3万以下" />
          <q-tab name="30000-50000" label="3-5万" />
          <q-tab name="50000-70000" label="5-7万" />
          <q-tab name="70000-90000" label="7-9万" />
          <q-tab name="90000-120000" label="9-12万" />
          <q-tab name="120000-160000" label="12-16万" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="price = '160000-200000'">
                <q-item-section>16-20万</q-item-section>
              </q-item>
              <q-item clickable @click="price = '200000-'">
                <q-item-section>20万以上</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
      </div>
    </div>
    <div class="q-pa-md row items-start q-gutter-md" style="width: 60%; margin-left: 20%">
      <q-card class="my-card" v-for="item in carList" :key="item.car_id" @click="goDetail(item.car_id)">
        <q-img src="car_details_test_img/1.png">
          <div class="absolute-bottom">
            <div class="text-h6">{{item.manufacturer}} {{item.model_name}}</div>
            <div class="text-subtitle1">{{item.service_life}}年</div>
            <div></div>
          </div>
        </q-img>

        <q-card-section>
          <div>
            <del class="text-h6">{{item.guide_price}}元</del>
            <span class="text-subtitle1 text-red">{{item.price}}元</span>
          </div>
        </q-card-section>
      </q-card>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination
        v-model="current"
        color="blue"
        :max="max_page"
        :max-pages="max_page"
        :boundary-numbers="false"
        direction-links
      />
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'Search',
  data () {
    return {
      text: '', // 搜索文字内容
      brand: '', // 品牌筛选
      body: '', // 车型筛选
      price: '', // 价格筛选
      carList: [], // 车辆信息列表
      current: 1, // 当前页码
      max_page: 1,
      city: ''
    }
  },
  methods: {
    /**
     * @description: 跳转到车辆详细信息页面
     * @param {Number} id
     * @return void
     */
    goDetail (id) {
      window.location = '/#/car/' + id.toString()
    },
    /**
     * @description: 获取满足条件的车辆列表
     * @return void
     */
    getCarsList () {
      let url = '/api/car/list?'
      if (this.brand !== '') {
        url += 'manufacturer=' + this.brand + '&'
      }
      if (this.body !== '') {
        url += 'body=' + this.body + '&'
      }
      if (this.price !== '') {
        const bar = this.price.indexOf('-')
        const min = this.price.substring(0, bar)
        const max = bar === this.price.length - 1 ? '' : this.price.substring(bar + 1)
        url += 'min-price=' + min + '&'
        if (max !== '') {
          url += 'max-price=' + max + '&'
        }
      }

      url = url.substr(0, url.length - 1)
      this.$axios.get(url).then(res => {
        if (res.status === 200) {
          this.carList = res.data
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

    const query = this.$route.query
    if (query.manufacturer !== null && query.manufacturer !== undefined) {
      this.brand = query.manufacturer
    }
    if (query.body !== null && query.body !== undefined) {
      this.body = query.body
    }
    if (query['min-guide-price'] !== null && query['min-guide-price'] !== undefined) {
      let p = query['min-guide-price'] + '-'
      if (query['max-guide-price'] !== null && query['max-guide-price'] !== undefined) {
        p += query['max-guide-price']
      }

      this.price = p
    }
    this.getCarsList()
  },
  watch: {
    brand: {
      handler () {
        this.getCarsList()
      }
    },
    body: {
      handler () {
        this.getCarsList()
      }
    },
    price: {
      handler () {
        this.getCarsList()
      }
    }
  }
}
</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>
