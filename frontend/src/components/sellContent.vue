<!--
 * @FileDescription: 填写卖车信息，预约卖车
 * @Author: ZoeChou
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/15
 -->
<template>
  <q-card style="width: 812px; max-width: 80vw;">
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
              style="width: 10vw"
            >
              <q-tab name="step1_sell" label="第1步 选择品牌车系" />
              <q-tab name="step2_sell" label="第2步 填写爱车车况" />
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
                      v-model="manufacture"
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
                            <img src="../../public/icons/大众.png" height="25" width="25"/>
                            <br/>大众
                          </div>
                        </div>
                      </template>
                      <template v-slot:宝马>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/宝马.png" height="25" width="25"/>
                            <br/>宝马
                          </div>
                        </div>
                      </template>
                      <template v-slot:奔驰>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/奔驰.png" height="25" width="25"/>
                            <br>奔驰
                          </div>
                        </div>
                      </template>
                      <template v-slot:丰田>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/丰田.png" height="25" width="25"/>
                            <br>丰田
                          </div>
                        </div>
                      </template>
                      <template v-slot:本田>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/本田.png" height="25" width="25"/>
                            <br>本田
                          </div>
                        </div>
                      </template>
                      <template v-slot:奥迪>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/奥迪.png" height="25" width="25"/>
                            <br>奥迪
                          </div>
                        </div>
                      </template>
                      <template v-slot:现代>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/现代.png" height="25" width="25"/>
                            <br>现代
                          </div>
                        </div>
                      </template>
                      <template v-slot:马自达>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/马自达.png" height="25" width="25"/>
                            <br>马自达
                          </div>
                        </div>
                      </template>
                      <template v-slot:哈弗>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/哈弗.png" height="25" width="25"/>
                            <br>哈弗
                          </div>
                        </div>
                      </template>
                      <template v-slot:比亚迪>
                        <div class="row items-center no-wrap">
                          <div class="text-center">
                            <img src="../../public/icons/比亚迪.png" height="25" width="25"/>
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
                      已选
                      <div style="width: 1vw" />
                      <img :src="require('../../public/icons/'+manufacture+'.png')" height="27" width="27"/>
                      {{manufacture}}
                    </div>
                    <q-option-group
                      v-model="series"
                      :options="seriesOfManufacturers[manufacture]"
                      color="primary"
                    />
                  </q-step>

                  <q-step
                    :name="3"
                    title="车型"
                  >
                    <div class="text-h6" style="display: flex; align-items: center">
                      已选
                      <div style="width: 1vw" />
                      <img :src="require('../../public/icons/'+manufacture+'.png')" height="27" width="27"/>
                      {{manufacture}} {{series}}
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
                      <q-btn @click="toSellStep2" color="primary" v-show="step === 3" label="完成"/>
                      <q-btn v-if="step > 1" flat color="primary" @click="$refs.stepper.previous()" label="返回" class="q-ml-sm" />
                    </q-stepper-navigation>
                  </template>
                </q-stepper>
              </q-tab-panel>

              <q-tab-panel name="step2_sell">
                <q-form @submit="uploadImage(0)" class="q-gutter-md">
                  <div class="text-h6" style="display: flex; align-items: center">
                    <img :src="require('../../public/icons/'+manufacture+'.png')" height="27" width="27"/>
                    <div style="width: 5px"/>
                    {{manufacture}} {{series}}
                  </div>
                  <div class="field-wrapper">
                    <fieldset>
                      <p class="field field-text">
                        <label for="server">使用时长</label>
                        <span class="field-suffixed">
                          <input aria-describedby="server-suffix" name="server" id="server" type="number" min="0" step="1" v-model="server_life" required/>
                          <span class="field-suffix" id="server-suffix">年</span>
                        </span>
                      </p>
                      <p class="field field-text">
                        <label for="mileage">行驶里程</label>
                        <span class="field-suffixed">
                          <input aria-describedby="mileage-suffix" name="mileage" id="mileage" type="number" min="0" step="1" v-model="mileage" required/>
                          <span class="field-suffix" id="mileage-suffix">万英里</span>
                        </span>
                      </p>
                    </fieldset>
                  </div>
                  <div class="field-wrapper">
                    <fieldset>
                      <legend>有尚未修复的损坏</legend>
                      <div style="height: 1vh"/>
                      <q-radio v-model="not_repaired_damage" label="是" :val="'有尚未修复的损坏'"/>
                      <q-radio v-model="not_repaired_damage" label="否" :val="'无尚未修复的损坏'"/>
                    </fieldset>
                  </div>
                  <div class="field-wrapper">
                    <fieldset>
                      <legend>上牌时间</legend>
                      <p class="field">
                        <q-date v-model="date" minimal/>
                      </p>
                    </fieldset>
                  </div>
                  <div class="field-wrapper">
                    <fieldset>
                      <q-input label="报价" type="number" v-model="price"/>
                    </fieldset>
                  </div>
                  <div class="field-wrapper">
                    <fieldset>
                      <legend>上传图片</legend>
                      <q-file v-model="files"
                              label="选择文件"
                              filled
                              counter
                              :counter-label="countFileLabel"
                              :max-files="5"
                              accept=".jpg, image/*"
                              multiple>
                        <template v-slot:prepend>
                          <q-icon name="attach_file" />
                        </template>
                      </q-file>
                    </fieldset>
                  </div>
                  <div style="height: 2vh"/>
                  <q-btn color="primary" label="预约卖车" type="submit"/>
                </q-form>
              </q-tab-panel>
            </q-tab-panels>
          </template>

        </q-splitter>
      </div>
    </div>
  </q-card>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import * as imageConversion from 'image-conversion'
import carInformationJson from '../../public/car_information/car_information.json'
// eslint-disable-next-line no-unused-vars
const carInformation = carInformationJson.car_information
export default {
  name: 'sellContent',
  data: function () {
    return {
      api_token: '', // 图床API密钥
      files: null, // 上传文件列表
      tab: 'step1_sell', // tab标签
      splitterModel: 20, // 分隔栏比例
      step: 1, // 步进控件当前步骤
      manufacture: '大众', // 汽车厂商
      series: '捷达', // 汽车系列
      type: '2019款梦想版1.5L自动时尚型', // 汽车类型
      mileage: 0, // 里程数
      city: sessionStorage.getItem('city'), // 当前城市
      not_repaired_damage: null, // 是否有未修复损伤
      date: null, // 上牌日期
      images: [], // 图片链接
      server_life: 0, // 使用年限
      price: 0, // 车主报价
      seriesOfManufacturers: {
        大众: [
          { label: '捷达', value: '捷达' },
          { label: '桑塔纳', value: '桑塔纳' }
        ],
        宝马: [
          { label: '宝马1系', value: '宝马1系' },
          { label: '宝马3系', value: '宝马3系' }
        ],
        奔驰: [
          { label: '奔驰CLK级', value: '奔驰CLK级' },
          { label: '奔驰CLS级', value: '奔驰CLS级' }
        ],
        丰田: [
          { label: '汉兰达', value: '汉兰达' },
          { label: '普拉多', value: '普拉多' }
        ],
        本田: [
          { label: '本田CR-V', value: '本田CRV' },
          { label: '思域', value: '思域' }
        ],
        奥迪: [
          { label: '奥迪A5', value: '奥迪A5' },
          { label: '奥迪A6', value: '奥迪A6' }
        ],
        现代: [
          { label: '途胜', value: '途胜' },
          { label: 'ix35', value: 'ix35' }
        ],
        马自达: [
          { label: '马自达3', value: '马自达3' },
          { label: '马自达6', value: '马自达6' }
        ],
        哈弗: [
          { label: '哈弗H6', value: '哈弗H6' },
          { label: '哈弗H2', value: '哈弗H2' }
        ],
        比亚迪: [
          { label: '比亚迪F3', value: '比亚迪F3' },
          { label: '比亚迪F0', value: '比亚迪F0' }
        ]
      }, // 厂商 -> 系列映射
      typesOfSeries: {
        捷达: [
          { label: '2019款 梦想版 1.5L 手动时尚型', value: '2019款梦想版1.5L手动时尚型' },
          { label: '2019款 梦想版 1.5L 自动时尚型', value: '2019款梦想版1.5L自动时尚型' }
        ],
        桑塔纳: [
          { label: '2018款 1.5L 自动风尚版', value: '2018款1.5L自动风尚版' },
          { label: '2018款 1.5L 自动舒适版', value: '2018款1.5L自动舒适版' }
        ],
        宝马1系: [
          { label: '宝马1系 2016款 118i 领先型(进口)', value: '宝马1系2016款118i领先型(进口)' },
          { label: '2013款 改款 116i 领先型(进口)', value: '2013款改款116i领先型(进口)' }
        ],
        宝马3系: [
          { label: '2017款 320Li 时尚型', value: '2017款320Li时尚型' },
          { label: '2012款 320i 豪华型', value: '2012款320i豪华型' }
        ],
        奔驰CLK级: [
          { label: '2006款 CLK 200K 双门轿跑车(进口)', value: '2006款CLK200K双门轿跑车(进口)' },
          { label: '2006款 CLK 200K 敞篷跑车(进口)', value: '2006款CLK200K敞篷跑车(进口)' }
        ],
        奔驰CLS级: [
          { label: '2020款 CLS 300 动感型(进口)', value: '2020款CLS300动感型(进口)}' },
          { label: '2018款 CLS 300 动感型(进口)', value: '2018款CLS300动感型(进口)' }
        ],
        汉兰达: [
          { label: '2009款 2.7L 两驱7座豪华版', value: '2009款2.7L两驱7座豪华版' },
          { label: '2015款 2.0T 四驱豪华版 7座', value: '2015款2.0T四驱豪华版 7座' }
        ],
        普拉多: [
          { label: '2016款 3.5L 自动TX-L', value: '2016款3.5L自动TX-L' },
          { label: '2016款 2.7L 自动标准版', value: '2016款2.7L自动标准版' }
        ],
        本田CRV: [
          { label: '2016款 2.0L 两驱都市版', value: '2016款2.0L两驱都市版' },
          { label: '2013款 2.0L 两驱经典版', value: '2013款 2.0L 两驱经典版' }
        ],
        思域: [
          { label: '2016款 220TURBO CVT豪华版', value: '2016款220TURBOCVT豪华版' }
        ],
        奥迪A5: [
          { label: '2016款 Sportback 45 TFSI(进口)', value: '2016款Sportback45TFSI(进口)' },
          { label: '2017款 Sportback 45 TFSI quattro 运动型(进口)', value: '2017款Sportback45TFSIquattro运动型(进口)' }
        ],
        奥迪A6: [
          { label: '2018款 3.0T allroad quattro(进口)', value: '2018款3.0Tallroadquattro(进口)' },
          { label: '2017款 Avant 40 TFSI 运动型(进口)', value: '2017款Avant40TFSI运动型(进口)' }
        ],
        途胜: [
          { label: '2015款 1.6T 双离合两驱智能型', value: '2015款1.6T双离合两驱智能型' },
          { label: '2018款 1.6T 双离合两驱15周年特别版', value: '2018款1.6T双离合两驱15周年特别版' }
        ],
        ix35: [
          { label: '2018款 2.0L 自动两驱智勇·畅享版', value: '2018款2.0L自动两驱智勇·畅享版' },
          { label: '2013款 2.0L 自动两驱智能型GLS 国IV', value: '2013款2.0L自动两驱智能型GLS国IV' }
        ],
        马自达3: [
          { label: '2012款 1.6L 自动经典特惠型', value: '2012款1.6L自动经典特惠型' },
          { label: '2007款 1.6L 手动标准型', value: '2007款1.6L手动标准型' }
        ],
        马自达6: [
          { label: '2011款 2.0L 自动时尚型', value: '2011款2.0L自动时尚型' },
          { label: '2007款 2.0L 手动型', value: '2007款2.0L手动型' }
        ],
        哈弗H6: [
          { label: '2013款 运动版 1.5T 手动两驱精英型', value: '2013款运动版1.5T手动两驱精英型' },
          { label: '2020款 1.5GDIT 自动铂金豪华版', value: '2020款1.5GDIT自动铂金豪华版' }
        ],
        哈弗H2: [
          { label: '2018款 红标 1.5T 双离合两驱豪华型', value: '2018款红标1.5T双离合两驱豪华型' },
          { label: '2016款 红标 1.5T 手动两驱精英型', value: '2016款红标1.5T手动两驱精英型' }
        ],
        比亚迪F3: [
          { label: '2018款 1.5L 手动经典型', value: '2018款 1.5L 手动经典型' },
          { label: '2015款 节能版 1.5L 手动豪华型', value: '2015款节能版1.5L手动豪华型' }
        ],
        比亚迪F0: [
          { label: '2012款 1.0L 铉酷型', value: '2012款1.0L铉酷型' },
          { label: '2011款 尚酷版 1.0L 悦酷型', value: '2011款尚酷版1.0L悦酷型' }
        ]
      } // 系列 -> 车辆名称映射
    }
  },
  methods: {
    /**
     * 进入第二步填写
     */
    toSellStep2 () {
      if (this.manufacture && this.series && this.type) {
        this.tab = 'step2_sell'
      } else {
        alert('请先完成第一步的填写！')
      }
    },
    /**
     * 表单数据检查
     */
    checkSellForm () {
      /* 检查所有变量是否都有值 */
      if (document.forms[0].checkValidity()) {
        return true
      } else {
        alert('表单无效，请您重新填写。')
        return false
      }
    },
    /**
     * 提交车辆信息
     */
    onSubmit () {
      this.checkSellForm()
      const carName = this.manufacture + '-' + this.series + '-' + this.type

      console.log(this.not_repaired_damage)

      this.$axios.post('api/car/new', {
        uid: sessionStorage.getItem('uid'),
        token: sessionStorage.getItem('token'),
        region: this.city,
        model_name: this.series + ' ' + this.type,
        guide_price: 4000000, // 待改
        manufacturer: this.manufacture,
        service_life: Number.parseInt(this.server_life),
        mileage: Number.parseInt(this.mileage),
        price: Number.parseInt(this.price),
        displacement: 2.0,
        body_type: carInformation[carName].body_type,
        fuel_type: carInformation[carName].fuel_type,
        gear_box: carInformation[carName].gearbox,
        power: Number.parseInt(carInformation[carName].power),
        not_repaired_damage: this.not_repaired_damage,
        imgs: this.images,
        date: this.date + ' 00:00:00',
        stat: '待售'
      }, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      })
        .then(function (response) {
          if (response.status === 200 && response.data.car_id !== '-1') {
            window.location = '/#/car/' + response.data.car_id
            window.location.reload()
          }
        })
    },
    /**
     * 返回图片上传提示文字
     * @param totalSize 总大小
     * @param filesNumber 文件数量
     * @param maxFiles 文件总数
     * @returns {string} 提示文字
     */
    countFileLabel ({ totalSize, filesNumber, maxFiles }) {
      return `${filesNumber} / ${maxFiles} 个文件 | 总大小： ${totalSize}`
    },
    /**
     * 递归上传图片文件
     * @param index 上传的文件下标
     */
    uploadImage (index) {
      if (index >= this.files.length) {
        this.onSubmit()
        return
      }

      const file = this.files[index]
      const self = this
      const reader = new FileReader()
      reader.readAsDataURL(file)
      reader.onloadend = function (e) {
        const formData = new FormData()
        const data = file
        imageConversion.compressAccurately(data, 60).then(res => {
          res = new File([res], file.name, { type: res.type, lastModified: Date.now() })
          formData.append('smfile', res)
          formData.append('Authorization', 'Ofarf02h3SOyeNsU3TSsq3XTqEmG1GSz')

          self.$axios.post('/cdn/upload', formData, {
            headers: {
              Authorization: 'Ofarf02h3SOyeNsU3TSsq3XTqEmG1GSz'
            }
          }).then((response) => {
            if (response.status === 200 && response.data.success) {
              self.images.push(response.data.data.url)
              self.uploadImage(index + 1)
            } else {
              alert('图片上传失败，请重试')
              self.images = []
            }
          }).catch(() => {
            alert('图片上传失败，请重试')
            self.images = []
          })
        })
      }
    }
  }
}
</script>

<style scoped>
  /* 把 fieldset 整合起来 */
  fieldset {
    border: 0;
    padding: 0.01px 0 0 0;
    margin: 0;
    min-width: 0;
    display: table-cell;
  }
  @-moz-document url-prefix() {
    fieldset {
      display: table-cell;
    }
  }
  .field-wrapper {
    padding: 1em;
    margin-bottom: 1em;
    border: 1px solid #eee;
    background-color: #fff;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.25);
  }
  :root .field-wrapper {
    border: 0;
  }
  legend {
    padding: 0 0 .5em 0;
    font-weight: bold;
    display: table;
  }

  /* 文本输入控件 */
  input, select {
    font: inherit;
  }
  .field-text {
    max-width: 20em;
  }
  .field-text label {
    cursor: pointer;
    font-weight: bold;
    margin-right: 1em;
  }
  .field-text label,
  .field-text input,
  .field-text select,
  .field-suffixed {
    width: 55%;
    box-sizing: border-box;
  }
  .field-text input,
  .field-text select {
    padding: .375em .3125em .3125em;
    border: 1px solid #ccc;
    border-radius: .25em;
    -webkit-appearance: none;
  }
  /* 处理聚焦状态 */
  .field-text input:focus,
  .field-text select:focus {
    box-shadow: 0 0 .5em rgba(93, 162, 248, 0.5);
    border-color: cornflowerblue;
    outline: 0;
  }

  /* 单选按钮 */
  input[type="radio"] {
    margin-right: .75em;
  }

  /* 行内布局 */
  .flexbox .field-text {
    display: flex;
    max-width: 28em;
  }
  .flexbox .field-text label {
    flex: 0 0 8em;
    display: flex;
    align-items: center;
  }
  .flexbox .field-suffixed {
    display: flex;
  }
  .flexbox .field-suffix {
    border-left: 0;
    border-radius: 0 .25em .25em 0;
    display: flex;
    align-items: center;
  }
  .flexbox .field-suffixed input select{
    max-width: 100%;
    border-radius: .25em 0 0 .25em;
  }

  .field-suffix {
    display: inline-block;
    /* 边框、颜色 */
    padding: .375em .3125em .3125em;
    border: 1px solid #ccc;
    border-radius: .25em;
  }
  .field-suffixed {
    max-width: 12em;
  }
</style>
