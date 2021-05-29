# Load the data from the csv file
dataDirectory <- "F:/TREC-RTS2016/25617/0.1JM_Rank/"
data1 <- read.csv('F:/TREC-RTS2016/25617/0.1JM_Rank/lmodelt.txt', sep=" ")
X_2017 <- read.csv('F:/TREC2017/Analysis/Ranklist/jm112/lmodelt.txt',sep=' ')

data1 <- read.csv('F:/TREC-RTS2016/25617/1000dirichlet/lmodelt.txt', sep=" ")
X_2017 <- read.csv('F:/TREC2017/Analysis/Ranklist/diri/lmodelt.txt',sep=' ')

data1 <- read.csv('F:/TREC-RTS2016/25617/bm25_list/lmodelt.txt', sep=" ")
X_2017 <- read.csv('F:/TREC2017/Analysis/Ranklist/bm25/lmodelt.txt',sep=' ')



library(data.table)

library(e1071)

#train_data <- data1[,c("topic","stdv","st","rt","mean","median","min","max")]
#setnames(train_data, old = c('stdv','st','rt','mean','median','min','max'), new = c('X','Y','YYRT','Z','A','B','C'))

#test_data <- X_2017[,c("topic","stdv","st","rt","mean","median","min","max")]
#setnames(test_data, old = c('stdv','st','rt','mean','median','min','max'), new = c('X','Y','YYRT','Z','A','B','C'))

train_data <- data1[,c("topic","stdv","st","rt","max")]
setnames(train_data, old = c('stdv','st','rt','max'), new = c('X','Y','YYRT','Z'))

test_data <- X_2017[,c("topic","stdv","st","rt","max")]
setnames(test_data, old = c('stdv','st','rt','max'), new = c('X','Y','YYRT','Z'))


# Plot the data
#plot(data, pch=16)

# Create a linear regression model
#model <- lm(Y ~ X+Z+A+B+C, train_data)
model <- lm(Y ~ X+Z, train_data)

# Add the fitted line
#abline(model)


predictedY <- predict(model, test_data)

test_data$st_pred_lr=predictedY

#data$lpredy = predictedY

# display the predictions
#points(data$X, predictedY, col = "blue", pch=4)

rmse <- function(error)
{
  sqrt(mean(error^2))
}

error <- model$residuals  # same as data$Y - predictedY
predictionRMSE_LR_st <- rmse(error)   # 5.703778
predictionRMSE_LR_st

#svrmodel_st <- svm(Y ~ X+Z+A+B+C , train_data)
svrmodel_st <- svm(Y ~ X+Z , train_data)

predictedSVR_st <- predict(svrmodel_st, test_data)

#points(data$X, predictedY, col = "red", pch=4)

# /!\ this time  svrModel$residuals  is not the same as data$Y - predictedY
# so we compute the error like this
error <- test_data$Y - predictedSVR_st
svrPredictionRMSE_st <- rmse(error)  # 3.157061
svrPredictionRMSE_st
# perform a grid search
tuneResult <- tune(svm, Y ~ X+Z,  data = train_data,
                   ranges = list(epsilon = seq(0,1,0.1), cost = 2^(2:3))
)
print(tuneResult)
# best performance: MSE = 8.371412, RMSE = 2.89 epsilon 1e-04 cost 4
# Draw the tuning graph
plot(tuneResult)

tuneResult <- tune(svm, Y ~ X+Z,  data = train_data,
                   ranges = list(epsilon = seq(0.25,0.35,0.01), cost = 2^(2:5))
) 


print(tuneResult)
plot(tuneResult)

tunedModel <- tuneResult$best.model

tunedModelY <- predict(tunedModel, test_data)

#points(data$X, predictedY, col = "green", pch=4)
#lines(data$X, predictedY, col = "green", pch=4)

#points(data$X, tunedModelY, col = "blue", pch=4)
#lines(data$X, tunedModelY, col = "blue", pch=4)

error <- test_data$Y - tunedModelY  

# this value can be different on your computer
# because the tune method  randomly shuffles the data
tunedModelRMSE <- rmse(error)  # 2.219642  
tunedModelRMSE

test_data$tunsvr_st = tunedModelY
test_data$svrpredy_st = predictedSVR_st



# Create a linear regression model
#modelrt <- lm(YYRT ~ X+Z+A+B+C, train_data)
modelrt <- lm(YYRT ~ X+Z, train_data)

# Add the fitted line
#abline(model)


predictedYYRT <- predict(modelrt, test_data)

test_data$pred_rt_lr=predictedYYRT

#data$lpredy = predictedY

# display the predictions
#points(data$X, predictedY, col = "blue", pch=4)

rmse <- function(error)
{
  sqrt(mean(error^2))
}

error <- modelrt$residuals  # same as data$Y - predictedY
predictionRMSE_rt <- rmse(error)   # 5.703778

predictionRMSE_rt
#svrmodel_rt <- svm(YYRT ~ X+Z+A+B+C , train_data)
svrmodel_rt <- svm(YYRT ~ X+Z , train_data)

predictedSVR_RT <- predict(svrmodel_rt, test_data)

#points(data$X, predictedY, col = "red", pch=4)

# /!\ this time  svrModel$residuals  is not the same as data$Y - predictedY
# so we compute the error like this
error <- test_data$YYRT - predictedSVR_RT
svrPredictionRMSE <- rmse(error)  # 3.157061
svrPredictionRMSE
# perform a grid search
tuneResult <- tune(svm, YYRT ~ X+Z,  data = train_data,
                   ranges = list(epsilon = seq(0,1,0.1), cost = 2^(2:3))
)
print(tuneResult)

tuneResult <- tune(svm, YYRT ~ X+Z,  data = train_data,
                   ranges = list(epsilon = seq(0.15,0.25,0.01), cost = 2^(2:5))
) 

print(tuneResult)

tunedModel <- tuneResult$best.model
tunedModelYYRT <- predict(tunedModel, test_data)

#points(data$X, predictedY, col = "green", pch=4)
#lines(data$X, predictedY, col = "green", pch=4)

#points(data$X, tunedModelY, col = "blue", pch=4)
#lines(data$X, tunedModelY, col = "blue", pch=4)

error <- test_data$YYRT - tunedModelYYRT

# this value can be different on your computer
# because the tune method  randomly shuffles the data
tunedModelRMSE <- rmse(error)  # 2.219642  

tunedModelRMSE
test_data$tune_Rt_SVR = tunedModelYYRT
  test_data$RT_SVR = predictedSVR_RT
print (mean(test_data$Y))
print (mean(test_data$st_pred_lr))
print (mean(test_data$svrpredy_st))
print(mean(test_data$tunsvr_st))

print (mean(test_data$YYRT))
print (mean(test_data$pred_rt_lr))
print (mean(test_data$RT_SVR))
print(mean(test_data$tune_Rt_SVR))

print (max(test_data$st_pred_lr))

print (mean(test_data$st_pred_lr))
print (max(test_data$svrpredy_st))
print(max(test_data$tunsvr_st))

print (max(test_data$YYRT))
print (max(test_data$pred_rt_lr))
print (max(test_data$RT_SVR))
print(max(test_data$tune_Rt_SVR))





#write.csv(test_data, file = "D:/datas/2017 data/threshold_new/Diri_stdv+max_predicted2017threshold.txt",row.names=FALSE)
write.csv(test_data, file = "D:/datas/2017 data/threshold_new/JM_stdv+max_predicted2017threshold.txt",row.names=FALSE)


#write.csv(test_data, file = "F:/TREC2017/Analysis/threshold/bm_predicted2017threshold.txt",row.names=FALSE)
