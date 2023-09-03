<template>
    <div class="card">
        <div class="haoyouliebiao">好友列表</div>
        <div v-for="item in friends.friends" :key="item.friendId">
            <div class="card-body row">
                <div class="friend-item col-3 img-field">
                    <div class="friend-img">
                        <img class="img-fluid" :src="item.friendPhoto" alt=""> 
                    </div>
                </div>
                <div class="col">
                    {{item.friendName}}
                </div>      
            </div> 
        </div>
    </div>
</template>
<script>

export default {
    props:{
        friends:{
            type:Object,
            require:true,
        }
    },
    setup(){

    },
    
}
</script>
<style scoped>
.friend-img{
    border-radius: 50%;
}
.haoyouliebiao{
    margin-left: 35%;
}
</style>