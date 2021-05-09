<!--
 * @FileDescription: 主页文件
 * @Author: ZoeChou
 * @Date: 2021/4/19
 * @LastEditors: ZoeChou
 * @LastEditTime: 2021/5/9
 -->
<template>
  <q-page>
    <!--ADD YOUR VUE CODE HERE-->
    <div class="flex flex-center column">
      <div class="row bg-blue-grey-2" style="min-height: 400px; width: 80%; padding: 24px;">
        <div id="parent" class="fit row inline  justify-center items-start content-start" style="overflow: hidden;">
          <div class="col-6" style="overflow: auto;min-height: 100px; max-height: 100px; ">
            <q-card>
              <q-card-section>
                <q-input></q-input>
              </q-card-section>
            </q-card>
          </div>,<div class="col-1" style="overflow: auto;min-height: 100px; max-height: 100px; ">
          <q-card>
            <q-card-section>
              <q-btn flat>搜索</q-btn>
            </q-card-section>
          </q-card>
        </div>,<div class="col-6      bg-grey-6" style="overflow: auto;min-height: 250px; max-height: 250px; ">
          <q-card>
            <q-card-section>
              Child #2
            </q-card-section>
          </q-card>
        </div>,<div class="col-4      bg-grey-6" style="overflow: auto;min-height: 250px; max-height: 250px; ">
          <q-card>
            <q-card-section>
              Child #3
            </q-card-section>
          </q-card>
        </div>,<div class="col-12      bg-grey-6" style="overflow: auto;min-height: 300px; max-height: 300px; ">
          <q-card>
            <q-card-section>
              Child #4
            </q-card-section>
          </q-card>
        </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex'
}
</script>
