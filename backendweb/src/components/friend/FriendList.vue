<template>
    <div class="card">
        <div class="haoyouliebiao">好友列表</div>
        <div v-for="item in friends.friends" :key="item.friendId">
            <div class="friendCard" @click="ClickName(item.friendName)">
                <div class="friendItem row">
                    <img class="friend-img col-3" :src="item.friendPhoto" alt="">
                    <p class="col-9 ">{{item.friendName}}</p>   
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
    setup(props , context){
        
        const ClickName = (name) => {
            context.emit("chatObjectName" , name);
        }

        return {
            ClickName,
        }
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
.friendCard{
    margin: 2% 2%;
    padding: 3% 3%;
    cursor:pointer;
}
.friendCard:hover{
    box-shadow: 2px 2px 10px lightblue;
    transition: 500ms;
}

</style>