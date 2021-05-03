<!--
 * @FileDescription: 车辆详细信息页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/3
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
            <span class="text-h4">{{car_info.manufacturer}} {{car_info.model_name}}</span>
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
                  <td>{{car_info.date}}</td>
                  <td>{{car_info.mileage}}</td>
                  <td>{{car_info.displacement}}</td>
                  <td>{{car_info.gear_box}}</td>
                </tr>
              </tbody>
            </q-markup-table>
          </div>
          <div style="margin-top: 2em">
            <div class="row" style="width: 100%">
              <span class="text-h6" style="margin-right: 5em">指导价格：{{car_info.guild_price}}</span>
              <span class="text-h6">售价：{{car_info.price}}</span>
            </div>
          </div>
          <div style="margin-top: 8em">
            <q-btn outline color="primary" size="lg" label="我要预约" style="margin-right: 5em"/>
            <q-btn outline color="primary" size="lg" label="车型对比"/>
          </div>
        </q-card>
      </div>
    </div>
    <q-separator/>
    <div class="row">
      <!--TODO:-->
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'CarDetail',
  data () {
    return {
      car_image_list: ['car_details_test_img/1.png', 'car_details_test_img/2.png',
        'car_details_test_img/3.png', 'car_details_test_img/4.png'], // 车辆照片集
      slide: 1, // 照片集当前下标
      car_info: {
        model_name: 'AE86', // 车型名称
        guild_price: 100000, // 指导价格
        manufacturer: '丰田', // 厂商
        service_life: 1.2, // 使用年限
        mileage: 1000, // 里程数
        displacement: 0, // 排量
        region_code: '114514', // 地区骂
        uid: '李田所', // 所有用户
        price: 80000, // 价格
        state: 1, // 状态
        body_type: 0, // 车身类型
        fuel_type: 0, // 燃料类型
        gear_box: 0, // 变速箱
        power: 0, // 马力
        not_repaired_damage: false, // 是否有修复过
        date: '2016-08' // 上牌日期
      } // 车辆详细信息
    }
  }
}
</script>

<style scoped>

</style>
