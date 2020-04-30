<template>
<!--    <div>-->
        <video style="width: 100%;height: 100%;object-fit: fill" :src="$store.state.studyVideo.VideoData" autoplay></video>
<!--    </div>-->

</template>

<script>
    export default {
        name: "studyVideo"
    }
</script>

<style scoped>
    .video{

        object-fit:fill;
    }
</style>
