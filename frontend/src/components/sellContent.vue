<template>
  <q-card style="width: 800px; max-width: 80vw;">
    <div class="column">
      <div class="col">
        <q-bar dark class="bg-primary text-white" style="height: 10vh">
          <div class="col text-weight-bold">
            Dejavu预约卖车，多卖20%，平均7天成交。
          </div>
        </q-bar>
      </div>
      <div class="col">
        <q-splitter
          v-model="splitterModel"
          style="height: 400px"
        >

          <template v-slot:before>
            <q-tabs
              v-model="tab"
              vertical
              class="bg-white text-primary"
              style="width: 12.5vw"
            >
              <q-tab name="step1_sell" label="第1步 选择品牌车系" />
              <q-tab name="step2_sell" label="第2步 填写爱车车况" />
              <q-tab name="step3_sell" label="第3步 预约车辆评估" />
            </q-tabs>
          </template>

          <template v-slot:after>
            <q-tab-panels
              v-model="tab"
              animated
              swipeable
              vertical
              transition-prev="jump-up"
              transition-next="jump-up"
            >
              <q-tab-panel name="step1_sell">
                <q-stepper
                  v-model="step"
                  ref="stepper"
                  color="primary"
                  header-class="text-bold"
                  animated
                  style="height: 350px"
                >
                  <q-step
                    :name="1"
                    title="品牌"
                    :done="step > 1"
                  >
                    <q-btn-toggle
                      v-model="manufacturer"
                      push
                      toggle-color="blue-4"
                      :options="[
                        { value: '大众' , slot: '大众' },
                        { value: '宝马' , slot: '宝马' },
                        { value: '奔驰' , slot: '奔驰' },
                        { value: '丰田' , slot: '丰田' },
                        { value: '本田' , slot: '本田' },
                        { value: '奥迪' , slot: '奥迪' },
                        { value: '现代' , slot: '现代' },
                        { value: '马自达' , slot: '马自达' },
                        { value: '哈弗' , slot: '哈弗' },
                        { value: '比亚迪' , slot: '比亚迪' }
                      ]"
                    >
                      <template v-slot:大众>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Volkswagen.png" height="25" width="25"/>
                            <br/>大众
                          </div>
                        </div>
                      </template>
                      <template v-slot:宝马>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/BMW.png" height="25" width="25"/>
                            <br/>宝马
                          </div>
                        </div>
                      </template>
                      <template v-slot:奔驰>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Benz.png" height="25" width="25"/>
                            <br>奔驰
                          </div>
                        </div>
                      </template>
                      <template v-slot:丰田>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Toyota.png" height="25" width="25"/>
                            <br>丰田
                          </div>
                        </div>
                      </template>
                      <template v-slot:本田>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Honda.png" height="25" width="25"/>
                            <br>本田
                          </div>
                        </div>
                      </template>
                      <template v-slot:奥迪>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Audi.png" height="25" width="25"/>
                            <br>奥迪
                          </div>
                        </div>
                      </template>
                      <template v-slot:现代>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Hyundai.png" height="25" width="25"/>
                            <br>现代
                          </div>
                        </div>
                      </template>
                      <template v-slot:马自达>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Mazda.png" height="25" width="25"/>
                            <br>马自达
                          </div>
                        </div>
                      </template>
                      <template v-slot:哈弗>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/Hafu.png" height="25" width="25"/>
                            <br>哈弗
                          </div>
                        </div>
                      </template>
                      <template v-slot:比亚迪>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/BYD.png" height="25" width="25"/>
                            <br>比亚迪
                          </div>
                        </div>
                      </template>
                    </q-btn-toggle>
                  </q-step>

                  <q-step
                    :name="2"
                    title="车系"
                    :done="step > 2"
                  >
                    <div class="text-h6" style="display: flex; align-items: center">
                      已选:
                      <img :src="require('../../public/icons/'+manufacturer+'.png')" height="27" width="27"/>
                      {{manufacturer}}
                    </div>
                    <q-option-group
                      :options="options"
                      type="radio"
                      v-model="series"
                    />
                    <q-option-group
                      v-model="series"
                      :options="seriesOfManufacturers[manufacturer]"
                      color="primary"
                    />
                  </q-step>

                  <q-step
                    :name="3"
                    title="车型"
                  >
                    <div class="text-h6" style="display: flex; align-items: center">
                      已选:<img :src="require('../../public/icons/'+manufacturer+'.png')" height="27" width="27"/>
                      {{manufacturer}} {{series}}
                    </div>
                    <q-option-group
                      v-model="type"
                      :options="typesOfSeries[series]"
                      color="primary"
                    />
                  </q-step>

                  <template v-slot:navigation>
                    <q-stepper-navigation>
                      <q-btn @click="$refs.stepper.next()" color="primary" v-show="step !== 3" label="下一步" />
                      <q-btn @click="tab='step2_sell'" color="primary" v-show="step === 3" label="完成"/>
                      <q-btn v-if="step > 1" flat color="primary" @click="$refs.stepper.previous()" label="返回" class="q-ml-sm" />
                    </q-stepper-navigation>
                  </template>
                </q-stepper>
              </q-tab-panel>

              <q-tab-panel name="step2_sell">
                <div class="text-h6" style="display: flex; align-items: center">
                  <img :src="require('../../public/icons/'+manufacturer+'.png')" height="27" width="27"/>
                  <div style="width: 5px"/>
                  {{manufacturer}} {{series}}
                </div>
                <div style="height: 3vh"/>
                <div class="row" style="align-items: center">
                  <div class="col-2">
                    <div class="text-h6">行驶里程</div>
                  </div>
                  <div class="col-8">
                    <q-input outlined v-model="mileage">
                      <template v-slot:append>万英里</template>
                    </q-input>
                  </div>
                </div>
                <div style="height: 2vh"/>
                <div class="row" style="align-items: center">
                  <div class="col-2">
                    <div class="text-h6">上牌时间</div>
                  </div>
                  <div class="col-4">
                    <q-select outlined v-model="year" :options="options" label="年" />
                  </div>
                  <div class="col-4">
                    <q-select outlined v-model="month" :options="options" label="月" />
                  </div>
                </div>
                <q-btn @click="tab='step3_sell'" color="primary" label="完成"/>
              </q-tab-panel>

              <q-tab-panel name="step3_sell">
              </q-tab-panel>
            </q-tab-panels>
          </template>

        </q-splitter>
      </div>
    </div>
  </q-card>
</template>

<script>
export default {
  name: 'sellContent',
  data: function () {
    return {
      tab: 'step1_sell',
      splitterModel: 20,
      step: 1,
      manufacturer: '大众',
      series: '',
      type: '',
      mileage: null,
      year: null,
      month: null,
      date: null,
      seriesOfManufacturers: {
        大众: [
          { label: '捷达', value: '捷达' },
          { label: '桑塔纳', value: '桑塔纳' },
          { label: '大众CC', value: '大众CC' }
        ],
        宝马: [
          { label: '宝马1系', value: '宝马1系' },
          { label: '宝马3系', value: '宝马3系' },
          { label: '宝马5系', value: '宝马5系' }
        ]
      },
      typesOfSeries: {
        捷达: [
          { label: '2019款 梦想版 1.5L 手动时尚型', value: '2019款 梦想版 1.5L 手动时尚型' },
          { label: '2019款 梦想版 1.5L 自动时尚型', value: '2019款 梦想版 1.5L 自动时尚型' },
          { label: '2019款 梦想版 1.5L 手动舒适型', value: '2019款 梦想版 1.5L 手动舒适型' }
        ],
        桑塔纳: [
          { label: '2021款 1.5L 自动风尚版', value: '2021款 1.5L 自动风尚版' },
          { label: '2021款 1.5L 自动舒适版', value: '2021款 1.5L 自动舒适版' },
          { label: '2021款 1.5L 手动风尚版', value: '2021款 1.5L 手动风尚版' }
        ],
        宝马1系: [
          { label: '2021款 125i M运动曜夜版', value: '2021款 125i M运动曜夜版' },
          { label: '2021款 改款 120i M运动曜夜版', value: '2021款 改款 120i M运动曜夜版' },
          { label: '2021款 改款 120i M运动版', value: '2021款 改款 120i M运动版' }
        ],
        宝马3系列: [
          { label: '2021款 330Li xDrive M运动曜夜套装', value: '2021款 330Li xDrive M运动曜夜套装' },
          { label: '2021款 改款 325i M运动套装', value: '2021款 改款 325i M运动套装' },
          { label: '2021款 330i M运动曜夜套装', value: '2021款 330i M运动曜夜套装' }
        ]
      }
    }
  }
}
</script>

<style scoped>

</style>
