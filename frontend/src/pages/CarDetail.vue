<!--
 * @FileDescription: 车辆详细信息页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/12
 -->
<template>
  <q-page class="flex">
    <div class="row" style="width: 100%; margin-top: 5em; margin-left: 2em">
      <div style="width: 35%; margin-left: 5%">
        <q-carousel
          v-model="slide"
          swipeable animated arrows infinite thumbnails
          :autoplay="true"
          class="rounded-borders">
          <q-carousel-slide
            v-for="(path, index) in car_image_list"
            :key="path"
            :name="index"
            :img-src="path"/>
        </q-carousel>
      </div>
      <div style="width: 5%"/>
      <div style="width: 45%; margin-right: 5%">
        <q-card flat>
          <div>
            <span class="text-h4">
              {{car_info.manufacturer}} {{car_info.model_name}}
              <q-badge outline color="green" align="middle" v-if="car_info.state === 0">
                等待出售
              </q-badge>
              <q-badge outline color="orange" align="middle" v-else-if="car_info.state === 1">
                正在议价
              </q-badge>
              <q-badge outline color="red" align="middle" v-else>
                已出售
              </q-badge>
            </span>
          </div>
          <div style="margin-top: 1em">
            <q-markup-table flat>
              <thead>
                <tr>
                  <th class="text-left">上牌时间</th>
                  <th class="text-left">里程数</th>
                  <th class="text-left">排量</th>
                  <th class="text-left">变速箱</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{{car_info.create_date}}</td>
                  <td>{{car_info.mileage}}</td>
                  <td>{{car_info.displacement}}</td>
                  <td>{{car_info.gear_box}}</td>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
          <div style="margin-top: 2em">
            <div class="row" style="width: 100%">
              <span class="text-h6" style="margin-right: 5em">指导价格：{{car_info.guide_price}}</span>
              <span class="text-h6">车主报价：{{car_info.price}}</span>
            </div>
          </div>
          <div style="margin-top: 8em">
            <q-btn push color="primary" size="lg" label="我要预约" style="margin-right: 2em" @click="show_dialog = true"/>
            <q-btn push color="primary" size="lg" label="车型对比"/>
          </div>
        </q-card>
      </div>
    </div>

    <div class="row" style="width:90%; margin-left: 5%;">
      <q-card flat style="width: 100%; margin-left: 2em">
        <div>
          <span class="text-h4">详细参数</span>
        </div>
        <div class="row" style="width: 100%; margin-top: 1em">
          <div style="width: 30%;" class="min">
            <q-toolbar class="bg-primary text-white shadow-2">
              <q-toolbar-title>品牌信息</q-toolbar-title>
            </q-toolbar>
            <q-markup-table class="min-height-box">
              <tbody>
                <tr>
                  <th class="text-subtitle2">生产厂商</th>
                  <th class="text-subtitle2">{{car_info.manufacturer}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">车型名称</th>
                  <th class="text-subtitle2">{{car_info.model_name}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">指导价格</th>
                  <th class="text-subtitle2">{{car_info.guide_price}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">车主报价</th>
                  <th class="text-subtitle2">{{car_info.price}}</th>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
          <div style="width: 30%; margin-left: 5%">
            <q-toolbar class="bg-primary text-white shadow-2">
              <q-toolbar-title>车型信息</q-toolbar-title>
            </q-toolbar>
            <q-markup-table class="min-height-box">
              <tbody>
                <tr>
                  <th class="text-subtitle2">排量</th>
                  <th class="text-subtitle2">{{car_info.displacement}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">车身类型</th>
                  <th class="text-subtitle2">{{car_info.body_type}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">燃料类型</th>
                  <th class="text-subtitle2">{{car_info.fuel_type}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">变速箱</th>
                  <th class="text-subtitle2">{{car_info.displacement}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">马力</th>
                  <th class="text-subtitle2">{{car_info.power}}</th>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
          <div style="width: 30%; margin-left: 5%">
            <q-toolbar class="bg-primary text-white shadow-2">
              <q-toolbar-title>使用信息</q-toolbar-title>
            </q-toolbar>
            <q-markup-table class="min-height-box">
              <tbody>
                <tr>
                  <th class="text-subtitle2">使用年限</th>
                  <th class="text-subtitle2">{{car_info.service_life}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">里程数</th>
                  <th class="text-subtitle2">{{car_info.mileage}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">是否修复</th>
                  <th class="text-subtitle2">{{car_info.not_repaired_damage}}</th>
                </tr>
                <tr>
                  <th class="text-subtitle2">上牌日期</th>
                  <th class="text-subtitle2">{{car_info.create_date}}</th>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
        </div>
      </q-card>
    </div>

    <q-dialog v-model="show_dialog" persistent>
      <q-card style="min-width: 40%" bordered>
        <bargain-dialog v-model="bargain_data" :price="car_info.price"/>
        <q-separator/>
        <q-card-actions align="right">
          <q-btn flat color="primary" v-close-popup label="预约买车"/>
          <q-btn flat color="primary" v-close-popup label="取消"/>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
import BargainDialog from 'components/BargainDialog'
export default {
  name: 'CarDetail',
  components: { BargainDialog },
  data () {
    return {
      car_image_list: ['car_details_test_img/1.png', 'car_details_test_img/2.png',
        'car_details_test_img/3.png', 'car_details_test_img/4.png'], // 车辆照片集
      slide: 1, // 照片集当前下标
      car_info: {
        model_name: '', // 车型名称
        guide_price: 0, // 指导价格
        manufacturer: '', // 厂商
        service_life: 0, // 使用年限
        mileage: 0, // 里程数
        displacement: 0, // 排量
        region: '', // 地区
        uid: '', // 所有用户
        price: 0, // 价格
        state: 0, // 状态
        body_type: 0, // 车身类型
        fuel_type: 0, // 燃料类型
        gear_box: 0, // 变速箱
        power: 0, // 马力
        not_repaired_damage: '', // 是否有修复过
        create_date: '' // 上牌日期
      }, // 车辆详细信息
      show_dialog: false, // 是否显示对话框
      bargain_data: {
        price: 0, // 买家报价
        time: {
          from: '', // 开始时间
          to: '' // 结束时间
        } // 联系时间区间
      } // 砍价数据
    }
  },
  beforeMount () {
    const id = this.$route.params.id
    this.$axios.get('/api/car?id=' + id).then(res => {
      if (res.status === 200 && res.data.model_name !== null && res.data.model_name !== undefined) {
        this.car_info = res.data
        this.car_info.create_date = this.car_info.create_date.substr(0, this.car_info.create_date.indexOf('T'))
      } else {
        window.location = '/car'
      }
    })
  }
}
</script>

<style scoped>
.min-height-box {
  min-height: 15em;
}

td, th {
  font-size: 16px;
}
</style>
